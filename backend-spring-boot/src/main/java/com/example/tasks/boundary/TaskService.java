package com.example.tasks.boundary;

import com.example.tasks.control.TaskProvider;
import com.example.tasks.control.WebSocketSessions;
import com.example.tasks.entity.Task;
import com.example.tasks.exception.NotFoundException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TaskService {

    private static final Function<String, String> OPEN_MESSAGE = (String name) -> new JSONObject()
            .put("name", name)
            .put("op", "open")
            .toString();

    private static final Function<String, String> CLOSE_MESSAGE = (String name) -> new JSONObject()
            .put("name", name)
            .put("op", "close")
            .toString();

    private final TaskProvider taskProvider;

    private final WebSocketSessions webSocketSessions;

    public TaskService(TaskProvider taskProvider, WebSocketSessions webSocketSessions) {
        this.taskProvider = taskProvider;
        this.webSocketSessions = webSocketSessions;
    }

    public Task read(final String name) {
        return this.taskProvider.read(name)
                .orElseThrow(NotFoundException::new);
    }

    public void open(final String name) {
        final Task task = this.taskProvider.read(name)
                .orElseThrow(NotFoundException::new);
        task.open();

        final String message = OPEN_MESSAGE.apply(name);
        this.webSocketSessions.send(message);
    }

    public void close(final String name) {
        final Task task = this.taskProvider.read(name)
                .orElseThrow(NotFoundException::new);
        task.close();

        final String message = CLOSE_MESSAGE.apply(name);
        this.webSocketSessions.send(message);
    }
}

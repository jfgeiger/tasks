package com.example.tasks.boundary;


import com.example.tasks.control.TaskProvider;
import com.example.tasks.control.WebSocketSessions;
import com.example.tasks.entity.Task;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class TasksService {

    private static final Function<String, String> CREATE_MESSAGE = (String name) -> new JSONObject()
            .put("name", name)
            .put("op", "create")
            .toString();

    private final TaskProvider taskProvider;

    private final WebSocketSessions webSocketSessions;

    public TasksService(TaskProvider taskProvider, WebSocketSessions webSocketSessions) {
        this.taskProvider = taskProvider;
        this.webSocketSessions = webSocketSessions;
    }

    public List<Task> read() {
        return this.taskProvider.read();
    }

    public void create(String name) {
        this.taskProvider.create(name);

        final String message = CREATE_MESSAGE.apply(name);
        this.webSocketSessions.send(message);
    }
}

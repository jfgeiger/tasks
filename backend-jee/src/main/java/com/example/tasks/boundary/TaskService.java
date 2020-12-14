package com.example.tasks.boundary;

import com.example.tasks.control.TaskProvider;
import com.example.tasks.control.WebSocketSessions;
import com.example.tasks.entity.Task;
import com.example.tasks.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import java.util.function.Function;

@Stateless
public class TaskService {

    private static final Function<String, String> OPEN_MESSAGE = (String name) -> Json.createObjectBuilder()
            .add("name", name)
            .add("op", "open")
            .build()
            .toString();

    private static final Function<String, String> CLOSE_MESSAGE = (String name) -> Json.createObjectBuilder()
            .add("name", name)
            .add("op", "close")
            .build()
            .toString();

    @EJB
    private TaskProvider taskProvider;

    @EJB
    private WebSocketSessions webSocketSessions;

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

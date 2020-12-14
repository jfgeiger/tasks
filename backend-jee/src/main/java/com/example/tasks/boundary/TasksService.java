package com.example.tasks.boundary;


import com.example.tasks.control.TaskProvider;
import com.example.tasks.control.WebSocketSessions;
import com.example.tasks.entity.Task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import java.util.List;
import java.util.function.Function;

@Stateless
public class TasksService {

    private static final Function<String, String> CREATE_MESSAGE = (String name) -> Json.createObjectBuilder()
            .add("name", name)
            .add("op", "create")
            .build()
            .toString();

    @EJB
    private TaskProvider taskProvider;

    @EJB
    private WebSocketSessions webSocketSessions;

    public List<Task> read() {
        return this.taskProvider.read();
    }

    public void create(final String name) {
        this.taskProvider.create(name);

        final String message = CREATE_MESSAGE.apply(name);
        this.webSocketSessions.send(message);
    }
}

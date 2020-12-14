package com.example.tasks.control;

import com.example.tasks.entity.Task;
import com.example.tasks.exception.TaskAlreadyExistingException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class TaskProvider {

    private final Map<String, Task> tasks = new HashMap<>();

    public List<Task> read() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> read(String name) {
        final Task task = this.tasks.get(name);

        return Optional.ofNullable(task);
    }

    public void create(String name) {
        final boolean existing = this.tasks.containsKey(name);

        if (existing) {
            throw new TaskAlreadyExistingException(name);
        } else {
            final Task task = new Task(name);
            this.tasks.put(name, task);
        }
    }
}

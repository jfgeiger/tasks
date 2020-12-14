package com.example.tasks.entity;

import java.util.Objects;

public class Task {

    private final String name;

    private boolean closed;

    public Task(String name, boolean closed) {
        this.name = name;
        this.closed = closed;
    }

    public Task(String name) {
        this(name, false);
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void open() {
        this.closed = false;
    }

    public void close() {
        this.closed = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        return name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

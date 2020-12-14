package com.example.tasks.exception;

public class TaskAlreadyExistingException extends RuntimeException {

    private final String name;

    public TaskAlreadyExistingException(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

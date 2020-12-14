package com.example.tasks.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SeeOtherException extends RuntimeException {

    public enum Type {
        TASK
    }

    private final Type type;

    private final String name;

    public SeeOtherException(final Type type, final String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

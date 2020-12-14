package com.example.tasks.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NotFoundException extends RuntimeException {
}

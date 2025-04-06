package org.example.hotelbookingsystem.exception;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, String details) {
        super(message, details);
    }
}
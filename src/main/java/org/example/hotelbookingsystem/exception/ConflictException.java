package org.example.hotelbookingsystem.exception;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, String details) {
        super(message, details);
    }
}
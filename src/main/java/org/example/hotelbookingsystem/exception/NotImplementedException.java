package org.example.hotelbookingsystem.exception;

public class NotImplementedException extends BaseException {
    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, String details) {
        super(message, details);
    }
}
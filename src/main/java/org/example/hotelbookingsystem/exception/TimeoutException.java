package org.example.hotelbookingsystem.exception;

public class TimeoutException extends BaseException {
    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, String details) {
        super(message, details);
    }
}
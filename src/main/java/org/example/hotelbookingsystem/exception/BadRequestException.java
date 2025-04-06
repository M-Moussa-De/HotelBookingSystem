package org.example.hotelbookingsystem.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, String details) {
        super(message, details);
    }
}
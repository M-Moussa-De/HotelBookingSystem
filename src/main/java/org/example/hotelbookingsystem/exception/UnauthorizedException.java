package org.example.hotelbookingsystem.exception;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, String details) {
        super(message, details);
    }
}
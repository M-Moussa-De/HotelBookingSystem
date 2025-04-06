package org.example.hotelbookingsystem.exception;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, String details) {
        super(message, details);
    }
}
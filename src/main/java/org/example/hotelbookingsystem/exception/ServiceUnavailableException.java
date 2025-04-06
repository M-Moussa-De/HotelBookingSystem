package org.example.hotelbookingsystem.exception;

public class ServiceUnavailableException extends BaseException {
    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, String details) {
        super(message, details);
    }
}
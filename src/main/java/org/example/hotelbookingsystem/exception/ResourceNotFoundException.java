package org.example.hotelbookingsystem.exception;

public class ResourceNotFoundException extends BaseException {
        public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, String details) {
        super(message, details);
    }
}
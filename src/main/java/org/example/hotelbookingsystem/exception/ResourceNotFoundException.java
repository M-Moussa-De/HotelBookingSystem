package org.example.hotelbookingsystem.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String details;

    public ResourceNotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}

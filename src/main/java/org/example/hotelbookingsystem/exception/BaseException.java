package org.example.hotelbookingsystem.exception;

public abstract class BaseException extends RuntimeException {
    private final String details;

    // Constructor
    public BaseException(String message) {
        super(message);
        this.details = "";
    }

    public BaseException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}

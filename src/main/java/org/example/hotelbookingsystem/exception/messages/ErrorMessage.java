package org.example.hotelbookingsystem.exception.messages;

public abstract class ErrorMessage {
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String BAD_REQUEST = "Bad request";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access";
    public static final String CONFLICT = "Conflict occurred";
    public static final String FORBIDDEN = "Forbidden";
    public static final String SERVICE_UNAVAILABLE = "Service unavailable";
    public static final String REQUEST_TIMEOUT = "Request timeout";
    public static final String NOT_IMPLEMENTED = "Not implemented";
    public static final String CREATION_FAILED = "Resource creation failed. Please try again.";
    public static final String SEEDING_SKIPPED = "Resource data already exists, skipping seeding.";
}
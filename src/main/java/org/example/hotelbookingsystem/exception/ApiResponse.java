package org.example.hotelbookingsystem.exception;

import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private String details;
    private int code;
    private T data;
    public ApiResponse() {
    }

    // Constructor
    public ApiResponse(boolean success, String message, int code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
        this.details = ""; // Set a default empty value if details are not provided
    }

    public ApiResponse(boolean success, String message, String details, int code, T data) {
        this.success = success;
        this.message = message;
        this.details = details;
        this.code = code;
        this.data = data;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Static methods to create common responses
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Request was successful", "", HttpStatus.OK.value(), data);
    }

    public static <T> ApiResponse<T> notFound(String message, String details) {
        return new ApiResponse<>(false, message, details, HttpStatus.NOT_FOUND.value(), null);
    }

    public static <T> ApiResponse<T> update() {
        return new ApiResponse<>(true, "Resource updated successfully", "", HttpStatus.NO_CONTENT.value(), null);
    }

    public static <T> ApiResponse<T> notModified(String message, String details) {
        return new ApiResponse<>(true, message, details, HttpStatus.NOT_MODIFIED.value(), null);
    }

    public static <T> ApiResponse<T> delete(String message, String details) {
        return new ApiResponse<>(true, message, details, HttpStatus.NO_CONTENT.value(), null);
    }

    public static <T> ApiResponse<T> creationFailed(String message, String details) {
        return new ApiResponse<>(false, message, details, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    public static <T> ApiResponse<T> error(String message, String details) {
        return new ApiResponse<>(false, message, details, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }
}
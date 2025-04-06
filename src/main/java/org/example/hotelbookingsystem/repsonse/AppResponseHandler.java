package org.example.hotelbookingsystem.repsonse;

import org.example.hotelbookingsystem.exception.messages.ErrorMessage;
import org.example.hotelbookingsystem.exception.messages.SuccessMessage;
import org.springframework.http.HttpStatus;

/**
 * The {@code AppResponseHandler} class provides a standardized way to construct and return
 * HTTP response messages. This class is designed to be used throughout the application to
 * handle both success and error responses with consistent format and status codes.
 *
 * @param <T> the type of data that can be included in the response
 */
public class AppResponseHandler<T> {
    private boolean success;
    private String message;
    private String details;
    private int code;
    private T data;

    /**
     * Default constructor for {@code AppResponseHandler}. Initializes an empty response.
     */
    public AppResponseHandler() {
    }

    /**
     * Constructs a new {@code AppResponseHandler} with the specified values.
     *
     * @param success a boolean indicating whether the request was successful
     * @param message a message describing the result of the operation
     * @param details additional details about the response
     * @param code    the HTTP status code associated with the response
     * @param data    the data to be included in the response (can be {@code null} if no data)
     */
    public AppResponseHandler(boolean success, String message, String details, int code, T data) {
        this.success = success;
        this.message = message;
        this.details = details != null ? details : "";
        this.code = code;
        this.data = data;
    }

    // <editor-fold desc="Getters">

    /**
     * Gets the success status of the response.
     *
     * @return {@code true} if the request was successful, {@code false} otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the message describing the result of the operation.
     *
     * @return the message of the response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets additional details about the response.
     *
     * @return the details of the response
     */
    public String getDetails() {
        return details;
    }

    /**
     * Gets the HTTP status code associated with the response.
     *
     * @return the status code of the response
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the data included in the response.
     *
     * @return the data of the response
     */
    public T getData() {
        return data;
    }

    // </editor-fold>

    // Static methods to create common responses

    /**
     * Creates a success response with the specified data.
     *
     * @param <T>  the type of the data
     * @param data the data to be included in the response
     * @return an {@code AppResponseHandler} with success status and the provided data
     */
    public static <T> AppResponseHandler<T> success(T data) {
        return new AppResponseHandler<>(true, SuccessMessage.REQUEST_SUCCESS, "", HttpStatus.OK.value(), data);
    }

    /**
     * Creates a response indicating that a resource was not found.
     *
     * @param <T>     the type of the data
     * @param details additional details about the error
     * @return an {@code AppResponseHandler} indicating a not found error with status code 404
     */
    public static <T> AppResponseHandler<T> notFound(String details) {
        return new AppResponseHandler<>(false, ErrorMessage.RESOURCE_NOT_FOUND, details, HttpStatus.NOT_FOUND.value(), null);
    }

    /**
     * Creates a response indicating that a resource was updated successfully.
     *
     * @param <T> the type of the data
     * @return an {@code AppResponseHandler} with success message and status code 204 (No Content)
     */
    public static <T> AppResponseHandler<T> update() {
        return new AppResponseHandler<>(true, SuccessMessage.RESOURCE_UPDATED, "", HttpStatus.NO_CONTENT.value(), null);
    }

    /**
     * Creates a response indicating that a resource was not modified.
     *
     * @param <T>     the type of the data
     * @param details additional details about the error
     * @return an {@code AppResponseHandler} with success message and status code 304 (Not Modified)
     */
    public static <T> AppResponseHandler<T> notModified(String details) {
        return new AppResponseHandler<>(true, SuccessMessage.RESOURCE_NOT_MODIFIED, details, HttpStatus.NOT_MODIFIED.value(), null);
    }

    /**
     * Creates a response indicating that a resource was deleted successfully.
     *
     * @param <T>     the type of the data
     * @param details additional details about the success
     * @return an {@code AppResponseHandler} with success message and status code 204 (No Content)
     */
    public static <T> AppResponseHandler<T> delete(String details) {
        return new AppResponseHandler<>(true, SuccessMessage.RESOURCE_DELETED, details, HttpStatus.NO_CONTENT.value(), null);
    }

    /**
     * Creates a response indicating that resource creation failed.
     *
     * @param <T>     the type of the data
     * @param details additional details about the failure
     * @return an {@code AppResponseHandler} indicating resource creation failure with status code 500
     */
    public static <T> AppResponseHandler<T> creationFailed(String details) {
        return new AppResponseHandler<>(false, ErrorMessage.CREATION_FAILED, details, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    /**
     * Creates a generic error response with the specified message and details.
     *
     * @param <T>     the type of the data
     * @param message the error message
     * @param details additional details about the error
     * @return an {@code AppResponseHandler} indicating an error with status code 500
     */
    public static <T> AppResponseHandler<T> error(String message, String details) {
        return new AppResponseHandler<>(false, message, details, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }
}

package org.example.hotelbookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // This handles all generic exceptions
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // It responds with a 500 status
    @ResponseBody  // The response will be in the body of the HTTP response
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.error("Internal Server Error", e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)  // Custom exception for not found resources
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ApiResponse.notFound(e.getMessage(), e.getDetails());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.error("Bad Request", e.getMessage());
    }
}
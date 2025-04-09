package org.example.hotelbookingsystem.exception;

import org.example.hotelbookingsystem.exception.messages.ErrorMessage;
import org.example.hotelbookingsystem.repsonse.AppResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler that catches various exceptions thrown in the application
 * and returns a corresponding HTTP response with the appropriate status code and message.
 * This class uses {@link ControllerAdvice} to globally handle exceptions thrown from
 * controllers and provide a consistent response structure.
 */
@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    /**
     * Handles all runtime generic exceptions.
     *
     * @param e the exception that was thrown
     * @return an {@link AppResponseHandler} containing an error message and status code 500
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppResponseHandler<?> handleRuntimeException(RuntimeException e) {
        logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        return AppResponseHandler.error(ErrorMessage.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * Handles {@link ResourceNotFoundException} (404).
     *
     * @param e the {@link ResourceNotFoundException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Not Found" error message and status code 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponseHandler<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.warn(ErrorMessage.RESOURCE_NOT_FOUND + ": {}", e.getMessage());
        return AppResponseHandler.notFound(e.getDetails());
    }

    /**
     * Handles {@link BadRequestException} (400).
     *
     * @param e the {@link BadRequestException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Bad Request" error message and status code 400
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponseHandler<?> handleBadRequestException(BadRequestException e) {
        logger.info(ErrorMessage.BAD_REQUEST + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.BAD_REQUEST, e.getMessage());
    }

    /**
     * Handles {@link UnauthorizedException} (401).
     *
     * @param e the {@link UnauthorizedException} that was thrown
     * @return an {@link AppResponseHandler} containing an "Unauthorized" error message and status code 401
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public AppResponseHandler<?> handleUnauthorizedException(UnauthorizedException e) {
        logger.info(ErrorMessage.UNAUTHORIZED_ACCESS + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.UNAUTHORIZED_ACCESS, e.getMessage());
    }

    /**
     * Handles {@link ConflictException} (409).
     *
     * @param e the {@link ConflictException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Conflict" error message and status code 409
     */
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public AppResponseHandler<?> handleConflictException(ConflictException e) {
        logger.info(ErrorMessage.CONFLICT + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.CONFLICT, e.getMessage());
    }

    /**
     * Handles {@link ForbiddenException} (403).
     *
     * @param e the {@link ForbiddenException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Forbidden" error message and status code 403
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public AppResponseHandler<?> handleForbiddenException(ForbiddenException e) {
        logger.info(ErrorMessage.FORBIDDEN + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.FORBIDDEN, e.getMessage());
    }

    /**
     * Handles {@link InternalServerErrorException} (500).
     *
     * @param e the {@link InternalServerErrorException} that was thrown
     * @return an {@link AppResponseHandler} containing an "Internal Server Error" message and status code 500
     */
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppResponseHandler<?> handleInternalServerErrorException(InternalServerErrorException e) {
        logger.error(ErrorMessage.INTERNAL_SERVER_ERROR + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * Handles {@link ServiceUnavailableException} (503).
     *
     * @param e the {@link ServiceUnavailableException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Service Unavailable" error message and status code 503
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public AppResponseHandler<?> handleServiceUnavailableException(ServiceUnavailableException e) {
        logger.error(ErrorMessage.SERVICE_UNAVAILABLE + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.SERVICE_UNAVAILABLE, e.getMessage());
    }

    /**
     * Handles {@link TimeoutException} (408).
     *
     * @param e the {@link TimeoutException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Request Timeout" error message and status code 408
     */
    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public AppResponseHandler<?> handleTimeoutException(TimeoutException e) {
        logger.error(ErrorMessage.REQUEST_TIMEOUT + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.REQUEST_TIMEOUT, e.getMessage());
    }

    /**
     * Handles {@link NotImplementedException} (501).
     *
     * @param e the {@link NotImplementedException} that was thrown
     * @return an {@link AppResponseHandler} containing a "Not Implemented" error message and status code 501
     */
    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public AppResponseHandler<?> handleNotImplementedException(NotImplementedException e) {
        logger.info(ErrorMessage.NOT_IMPLEMENTED + ": {}", e.getMessage());
        return AppResponseHandler.error(ErrorMessage.NOT_IMPLEMENTED, e.getMessage());
    }
}

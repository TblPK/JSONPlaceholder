package com.example.demo.advice;

import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler advice for REST controllers.
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Handles UserNotFoundException and returns a response with HTTP status 404 Not Found.
     *
     * @param ex The exception object.
     * @return A message describing the exception.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles RoleNotFoundException and returns a response with HTTP status 404 Not Found.
     * @param ex The exception object.
     * @return A message describing the exception.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public String handleRoleNotFoundException(RoleNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles UserAlreadyExistsException and returns a response with HTTP status 409 Conflict.
     * @param ex The exception object.
     * @return A message describing the exception.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }
}

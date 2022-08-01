package com.gfa.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleInvalidPasswordLengthException(RuntimeException e) {
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(MissingUsernameOrPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleMissingUsernameOrPasswordException(RuntimeException e) {
        return new ErrorDTO(e.getMessage());
    }
}

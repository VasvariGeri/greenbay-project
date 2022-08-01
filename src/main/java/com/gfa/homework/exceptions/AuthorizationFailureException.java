package com.gfa.homework.exceptions;

public class AuthorizationFailureException extends RuntimeException {

    public AuthorizationFailureException() {
    }

    public AuthorizationFailureException(String message) {
        super(message);
    }
}

package com.gfa.homework.exceptions;

public class MissingUsernameOrPasswordException extends RuntimeException{

    public MissingUsernameOrPasswordException() {}

    public MissingUsernameOrPasswordException(String message) {
        super(message);
    }
}

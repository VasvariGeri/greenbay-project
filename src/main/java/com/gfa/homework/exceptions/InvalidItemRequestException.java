package com.gfa.homework.exceptions;

public class InvalidItemRequestException extends RuntimeException {

    public InvalidItemRequestException() {
    }

    public InvalidItemRequestException(String message) {
        super(message);
    }
}

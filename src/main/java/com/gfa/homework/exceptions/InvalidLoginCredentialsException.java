package com.gfa.homework.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException {

  public InvalidLoginCredentialsException() {
  }
  public InvalidLoginCredentialsException(String message) {
    super(message);
  }
}

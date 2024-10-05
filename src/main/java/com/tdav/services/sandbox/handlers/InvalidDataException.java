package com.tdav.services.sandbox.handlers;

public class InvalidDataException extends RuntimeException {

  public InvalidDataException(String message) {
    super(message);
  }

  public InvalidDataException(String message, Throwable cause) {
    super(message, cause);
  }

}

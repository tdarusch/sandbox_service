package com.tdav.services.sandbox.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage() != null ? ex.getMessage() : "Not found.", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<String> handleInvalidDataException(InvalidDataException ex) {
    return new ResponseEntity<>(ex.getMessage() != null ? ex.getMessage() : "Invalid request.", HttpStatus.BAD_REQUEST);
  }

}

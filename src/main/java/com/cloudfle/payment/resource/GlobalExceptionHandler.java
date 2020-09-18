package com.cloudfle.payment.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cloudfle.payment.exception.PaymentAlreadyExistentException;
import com.cloudfle.payment.exception.PaymentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @Autowired
  private Logger logger;

  @ExceptionHandler (PaymentNotFoundException.class)
  public ResponseEntity<Object> handlePaymentNotFoundException(PaymentNotFoundException e) {
    logger.error("PaymentNotFoundException, message: {}", e.getMessage());
    return new ResponseEntity<>(RestControllerAdvice.getErrorResponseEnvelope(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler (PaymentAlreadyExistentException.class)
  public ResponseEntity<Object> handlePaymentAlreadyExistentException(PaymentAlreadyExistentException e) {
    logger.error("PaymentAlreadyExistentException, message: {}", e.getMessage());
    return new ResponseEntity<>(RestControllerAdvice.getErrorResponseEnvelope(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler (MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    logger.error("MethodArgumentNotValidException, message: {}", e.getMessage());
    if (e.getBindingResult() == null) {
      return new ResponseEntity<>(RestControllerAdvice.getErrorResponseEnvelope(e.getMessage()),
        HttpStatus.BAD_REQUEST);
    }
    List<String> errors = e.getBindingResult().getAllErrors().stream()
      .map(ObjectError::getDefaultMessage)
      .collect(Collectors.toList());
    return new ResponseEntity<>(RestControllerAdvice.getErrorResponseEnvelope(errors), HttpStatus.BAD_REQUEST);
  }
}

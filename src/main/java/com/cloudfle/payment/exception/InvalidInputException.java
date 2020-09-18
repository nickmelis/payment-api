package com.cloudfle.payment.exception;

public class InvalidInputException extends PaymentAPIException {

  private static final long serialVersionUID = -1851060114367300069L;

  public InvalidInputException() {
  }

  public InvalidInputException(
      String message) {
    super(message);
  }

  public InvalidInputException(
      Throwable cause) {
    super(cause);
  }

  public InvalidInputException(
      String message,
      Throwable cause) {
    super(message, cause);
  }

  public InvalidInputException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

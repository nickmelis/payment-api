package com.cloudfle.payment.exception;

/**
 * Exception representing an unsuccessful operation that assumes a payment does not yet exist.
 */
public class PaymentAlreadyExistentException extends PaymentAPIException {

  private static final long serialVersionUID = -2403257399396011966L;

  public PaymentAlreadyExistentException() {
  }

  public PaymentAlreadyExistentException(
      String message) {
    super(message);
  }

  public PaymentAlreadyExistentException(
      Throwable cause) {
    super(cause);
  }

  public PaymentAlreadyExistentException(
      String message,
      Throwable cause) {
    super(message, cause);
  }

  public PaymentAlreadyExistentException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

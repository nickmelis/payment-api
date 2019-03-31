package tech.form3.payment.exception;

public class PaymentAPIException extends Exception {

  private static final long serialVersionUID = 1452679658357111083L;

  public PaymentAPIException() {
  }

  public PaymentAPIException(
      String message) {
    super(message);
  }

  public PaymentAPIException(
      Throwable cause) {
    super(cause);
  }

  public PaymentAPIException(
      String message,
      Throwable cause) {
    super(message, cause);
  }

  public PaymentAPIException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

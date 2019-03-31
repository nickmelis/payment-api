package tech.form3.payment.exception;

/**
 * Exception representing an unsuccessful payment search by id.
 */
public class PaymentNotFoundException extends PaymentAPIException {

  private static final long serialVersionUID = -1117496922462540336L;

  public PaymentNotFoundException() {
  }

  public PaymentNotFoundException(
      String message) {
    super(message);
  }

  public PaymentNotFoundException(
      Throwable cause) {
    super(cause);
  }

  public PaymentNotFoundException(
      String message,
      Throwable cause) {
    super(message, cause);
  }

  public PaymentNotFoundException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

package tech.form3.payment.model.mapper;

import tech.form3.payment.model.PaymentType;

public class PaymentTypeMapper {

  private PaymentTypeMapper() {
    super();
  }

  public static PaymentType map(tech.form3.payment.domain.PaymentType domain) {
    if (domain == null) {
      return null;
    }

    switch (domain) {
      case PAYMENT:
        return PaymentType.PAYMENT;
      default:
        throw new UnsupportedOperationException("Payment type not supported: " + domain);
    }
  }

  public static tech.form3.payment.domain.PaymentType map(PaymentType model) {
    if (model == null) {
      return null;
    }

    switch (model) {
      case PAYMENT:
        return tech.form3.payment.domain.PaymentType.PAYMENT;
      default:
        throw new UnsupportedOperationException("Payment type not supported: " + model);
    }
  }
}

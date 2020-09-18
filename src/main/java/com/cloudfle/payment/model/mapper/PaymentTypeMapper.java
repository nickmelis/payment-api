package com.cloudfle.payment.model.mapper;

import com.cloudfle.payment.model.PaymentType;

public class PaymentTypeMapper {

  private PaymentTypeMapper() {
    super();
  }

  public static PaymentType map(com.cloudfle.payment.domain.PaymentType domain) {
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

  public static com.cloudfle.payment.domain.PaymentType map(PaymentType model) {
    if (model == null) {
      return null;
    }

    switch (model) {
      case PAYMENT:
        return com.cloudfle.payment.domain.PaymentType.PAYMENT;
      default:
        throw new UnsupportedOperationException("Payment type not supported: " + model);
    }
  }
}

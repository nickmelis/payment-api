package com.cloudfle.payment.model.mapper;

import com.cloudfle.payment.model.SenderCharge;

public class SenderChargeMapper {

  private SenderChargeMapper() {
    super();
  }

  public static SenderCharge map(com.cloudfle.payment.domain.SenderCharge domain) {
    if (domain == null) {
      return null;
    }

    return SenderCharge.builder()
      .withAmount(domain.getAmount())
      .withCurrency(domain.getCurrency())
      .build();
  }

  public static com.cloudfle.payment.domain.SenderCharge map(SenderCharge model) {
    if (model == null) {
      return null;
    }

    return com.cloudfle.payment.domain.SenderCharge.builder()
      .withAmount(model.getAmount())
      .withCurrency(model.getCurrency())
      .build();
  }
}

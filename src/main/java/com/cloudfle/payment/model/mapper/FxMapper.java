package com.cloudfle.payment.model.mapper;

import com.cloudfle.payment.model.Fx;

public class FxMapper {

  private FxMapper() {
    super();
  }

  public static Fx map(com.cloudfle.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }

    return Fx.builder()
      .withContractReference(domain.getFxContractReference())
      .withExchangeRate(domain.getFxExchangeRate())
      .withOriginalAmount(domain.getFxOriginalAmount())
      .withOriginalCurrency(domain.getFxOriginalCurrency())
      .build();
  }
}

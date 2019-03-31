package tech.form3.payment.model.mapper;

import tech.form3.payment.model.Fx;

public class FxMapper {

  private FxMapper() {
    super();
  }

  public static Fx map(tech.form3.payment.domain.Payment domain) {
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

package tech.form3.payment.model.mapper;

import tech.form3.payment.model.SenderCharge;

public class SenderChargeMapper {

  private SenderChargeMapper() {
    super();
  }

  public static SenderCharge map(tech.form3.payment.domain.SenderCharge domain) {
    if (domain == null) {
      return null;
    }

    return SenderCharge.builder()
      .withAmount(domain.getAmount())
      .withCurrency(domain.getCurrency())
      .build();
  }

  public static tech.form3.payment.domain.SenderCharge map(SenderCharge model) {
    if (model == null) {
      return null;
    }

    return tech.form3.payment.domain.SenderCharge.builder()
      .withAmount(model.getAmount())
      .withCurrency(model.getCurrency())
      .build();
  }
}

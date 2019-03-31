package tech.form3.payment.model.mapper;

import tech.form3.payment.model.SponsorParty;

public class SponsorPartyMapper {

  private SponsorPartyMapper() {
    super();
  }

  public static SponsorParty map(tech.form3.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }

    return SponsorParty.builder()
      .withAccountNumber(domain.getSponsorPartyAccountNumber())
      .withBankId(domain.getSponsorPartyBankId())
      .withBankIdCode(domain.getSponsorPartyBankIdCode())
      .build();
  }
}

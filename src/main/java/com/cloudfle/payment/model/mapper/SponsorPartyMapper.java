package com.cloudfle.payment.model.mapper;

import com.cloudfle.payment.model.SponsorParty;

public class SponsorPartyMapper {

  private SponsorPartyMapper() {
    super();
  }

  public static SponsorParty map(com.cloudfle.payment.domain.Payment domain) {
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

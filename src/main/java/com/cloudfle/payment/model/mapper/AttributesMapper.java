package com.cloudfle.payment.model.mapper;

import com.cloudfle.payment.model.Attributes;

public class AttributesMapper {

  private AttributesMapper() {
    super();
  }

  public static Attributes map(com.cloudfle.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }
    return Attributes.builder()
      .withAmount(domain.getAmount())
      .withBeneficiaryParty(BeneficiaryPartyMapper.map(domain))
      .withChargesInformation(ChargesInformationMapper.map(domain))
      .withCurrency(domain.getCurrency())
      .withDebtorParty(DebtorPartyMapper.map(domain))
      .withEndToEndReference(domain.getEndToEndReference())
      .withFx(FxMapper.map(domain))
      .withNumericReference(domain.getNumericReference())
      .withPaymentId(domain.getPaymentId())
      .withPaymentPurpose(domain.getPaymentPurpose())
      .withPaymentScheme(domain.getPaymentScheme())
      .withPaymentType(domain.getPaymentType())
      .withProcessingDate(domain.getProcessingDate())
      .withReference(domain.getReference())
      .withSchemePaymentSubType(domain.getSchemePaymentSubType())
      .withSchemePaymentType(domain.getSchemePaymentType())
      .withSponsorParty(SponsorPartyMapper.map(domain))
      .build();
  }
}

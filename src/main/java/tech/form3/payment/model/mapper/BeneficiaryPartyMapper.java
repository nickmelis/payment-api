package tech.form3.payment.model.mapper;

import tech.form3.payment.model.BeneficiaryParty;

public class BeneficiaryPartyMapper {

  private BeneficiaryPartyMapper() {
    super();
  }

  public static BeneficiaryParty map(tech.form3.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }

    return BeneficiaryParty.builder()
      .withAccountName(domain.getBeneficiaryPartyAccountName())
      .withAccountNumber(domain.getBeneficiaryPartyAccountNumber())
      .withAccountNumberCode(domain.getBeneficiaryPartyAccountNumberCode())
      .withAccountType(domain.getBeneficiaryPartyAccountType())
      .withAddress(domain.getBeneficiaryPartyAddress())
      .withBankId(domain.getBeneficiaryPartyBankId())
      .withBankIdCode(domain.getBeneficiaryPartyBankIdCode())
      .withName(domain.getBeneficiaryPartyName())
      .build();
  }
}

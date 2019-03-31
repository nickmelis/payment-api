package tech.form3.payment.model.mapper;

import tech.form3.payment.model.DebtorParty;

public class DebtorPartyMapper {

  private DebtorPartyMapper() {
    super();
  }

  public static DebtorParty map(tech.form3.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }

    return DebtorParty.builder()
      .withAccountName(domain.getDebtorPartyAccountName())
      .withAccountNumber(domain.getDebtorPartyAccountNumber())
      .withAccountNumberCode(domain.getDebtorPartyAccountNumberCode())
      .withAddress(domain.getDebtorPartyAddress())
      .withBankId(domain.getDebtorPartyBankId())
      .withBankIdCode(domain.getDebtorPartyBankIdCode())
      .withName(domain.getDebtorPartyName())
      .build();
  }
}

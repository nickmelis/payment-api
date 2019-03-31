package tech.form3.payment.model.mapper;

import java.util.stream.Collectors;

import tech.form3.payment.model.Payment;

public class PaymentMapper {

  public static Payment map(tech.form3.payment.domain.Payment domain) {
    return Payment.builder()
      .withAttributes(AttributesMapper.map(domain))
      .withId(domain.getId())
      .withOrganisationId(domain.getOrganisationId())
      .withType(PaymentTypeMapper.map(domain.getType()))
      .withVersion(domain.getVersion())
      .build();
  }

  public static tech.form3.payment.domain.Payment map(Payment model) {
    tech.form3.payment.domain.Payment.Builder builder = tech.form3.payment.domain.Payment.builder()
      .withId(model.getId())
      .withOrganisationId(model.getOrganisationId())
      .withType(PaymentTypeMapper.map(model.getType()))
      .withVersion(model.getVersion());
    // Attributes
    if (model.getAttributes() != null) {
      builder.withAmount(model.getAttributes().getAmount())
      .withCurrency(model.getAttributes().getCurrency())
      .withEndToEndReference(model.getAttributes().getEndToEndReference())
      .withNumericReference(model.getAttributes().getNumericReference())
      .withPaymentId(model.getAttributes().getPaymentId())
      .withPaymentPurpose(model.getAttributes().getPaymentPurpose())
      .withPaymentScheme(model.getAttributes().getPaymentScheme())
      .withPaymentType(model.getAttributes().getPaymentType())
      .withProcessingDate(model.getAttributes().getProcessingDate())
      .withReference(model.getAttributes().getReference())
      .withSchemePaymentSubType(model.getAttributes().getSchemePaymentSubType())
      .withSchemePaymentType(model.getAttributes().getSchemePaymentType());
    }
    // Beneficiary party
    if (model.getAttributes() != null && model.getAttributes().getBeneficiaryParty() != null) {
      builder.withBeneficiaryPartyAccountName(model.getAttributes().getBeneficiaryParty().getAccountName())
      .withBeneficiaryPartyAccountNumber(model.getAttributes().getBeneficiaryParty().getAccountNumber())
      .withBeneficiaryPartyAccountNumberCode(model.getAttributes().getBeneficiaryParty().getAccountNumberCode())
      .withBeneficiaryPartyAccountType(model.getAttributes().getBeneficiaryParty().getAccountType())
      .withBeneficiaryPartyAddress(model.getAttributes().getBeneficiaryParty().getAddress())
      .withBeneficiaryPartyBankId(model.getAttributes().getBeneficiaryParty().getBankId())
      .withBeneficiaryPartyBankIdCode(model.getAttributes().getBeneficiaryParty().getBankIdCode())
      .withBeneficiaryPartyName(model.getAttributes().getBeneficiaryParty().getName());
    }
    // Charges information
    if (model.getAttributes() != null && model.getAttributes().getChargesInformation() != null) {
      builder.withChargesInformationBearerCode(model.getAttributes().getChargesInformation().getBearerCode())
      .withChargesInformationReceiverChargesAmount(
        model.getAttributes().getChargesInformation().getReceiverChargesAmount())
      .withChargesInformationReceiverChargesCurrency(
        model.getAttributes().getChargesInformation().getReceiverChargesCurrency())
      .withChargesInformationSenderCharges(model.getAttributes().getChargesInformation().getSenderCharges().stream()
        .map(SenderChargeMapper::map)
          .collect(Collectors.toSet()));
    }
    // Debtor party
    if (model.getAttributes() != null && model.getAttributes().getDebtorParty() != null) {
      builder.withDebtorPartyAccountName(model.getAttributes().getDebtorParty().getAccountName())
      .withDebtorPartyAccountNumber(model.getAttributes().getDebtorParty().getAccountNumber())
      .withDebtorPartyAccountNumberCode(model.getAttributes().getDebtorParty().getAccountNumberCode())
      .withDebtorPartyAddress(model.getAttributes().getDebtorParty().getAddress())
      .withDebtorPartyBankId(model.getAttributes().getDebtorParty().getBankId())
      .withDebtorPartyBankIdCode(model.getAttributes().getDebtorParty().getBankIdCode())
      .withDebtorPartyName(model.getAttributes().getDebtorParty().getName());
    }
    // Fx
    if (model.getAttributes() != null && model.getAttributes().getFx() != null) {
      builder.withFxContractReference(model.getAttributes().getFx().getContractReference())
      .withFxExchangeRate(model.getAttributes().getFx().getExchangeRate())
      .withFxOriginalAmount(model.getAttributes().getFx().getOriginalAmount())
      .withFxOriginalCurrency(model.getAttributes().getFx().getOriginalCurrency());
    }
    // Sponsor party
    if (model.getAttributes() != null && model.getAttributes().getSponsorParty() != null) {
      builder.withSponsorPartyAccountNumber(model.getAttributes().getSponsorParty().getAccountNumber())
      .withSponsorPartyBankId(model.getAttributes().getSponsorParty().getBankId())
      .withSponsorPartyBankIdCode(model.getAttributes().getSponsorParty().getBankIdCode());
    }
    return builder.build();
  }
}

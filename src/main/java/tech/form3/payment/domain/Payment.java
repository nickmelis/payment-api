package tech.form3.payment.domain;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "payments")
public class Payment {
  @Id
  @NotBlank (message = "Payment id is mandatory")
  @Column (name = "payment_id")
  private String id;

  @NotNull
  @Column (name = "payment_type")
  private PaymentType type;

  @NotNull
  @Column (name = "version")
  private Integer version;

  @NotBlank (message = "Organisation id is mandatory")
  @Column (name = "organisation_id")
  private String organisationId;

  @Column (name = "attribute_amount")
  private String amount;

  @Column (name = "attribute_beneficiaryParty_account_name")
  private String beneficiaryPartyAccountName;

  @Column (name = "attribute_beneficiaryParty_account_number")
  private String beneficiaryPartyAccountNumber;

  @Column (name = "attribute_beneficiaryParty_account_number_code")
  private String beneficiaryPartyAccountNumberCode;

  @Column (name = "attribute_beneficiaryParty_account_type")
  private Integer beneficiaryPartyAccountType;

  @Column (name = "attribute_beneficiaryParty_address")
  private String beneficiaryPartyAddress;

  @Column (name = "attribute_beneficiaryParty_bank_id")
  private String beneficiaryPartyBankId;

  @Column (name = "attribute_beneficiaryParty_bank_id_code")
  private String beneficiaryPartyBankIdCode;

  @Column (name = "attribute_beneficiaryParty_name")
  private String beneficiaryPartyName;

  @Column (name = "attribute_chargesInformation_bearer_code")
  private String chargesInformationBearerCode;

  //  @Column (name = "attribute_chargesInformation_sender_charges")
  @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn (name = "payment_id")
  private Set<SenderCharge> chargesInformationSenderCharges;

  @Column (name = "attribute_chargesInformation_receiver_charges_amount")
  private String chargesInformationReceiverChargesAmount;

  @Column (name = "attribute_chargesInformation_receiver_charges_currency")
  private String chargesInformationReceiverChargesCurrency;

  @Column (name = "attribute_currency")
  private String currency;

  @Column (name = "attribute_debtorParty_account_name")
  private String debtorPartyAccountName;

  @Column (name = "attribute_debtorParty_account_number")
  private String debtorPartyAccountNumber;

  @Column (name = "attribute_debtorParty_account_number_code")
  private String debtorPartyAccountNumberCode;

  @Column (name = "attribute_debtorParty_address")
  private String debtorPartyAddress;

  @Column (name = "attribute_debtorParty_bank_id")
  private String debtorPartyBankId;

  @Column (name = "attribute_debtorParty_bank_id_code")
  private String debtorPartyBankIdCode;

  @Column (name = "attribute_debtorParty_name")
  private String debtorPartyName;

  @Column (name = "attribute_endToEndReference")
  private String endToEndReference;

  @Column (name = "attribute_fx_contract_reference")
  private String fxContractReference;

  @Column (name = "attribute_fx_exchange_rate")
  private String fxExchangeRate;

  @Column (name = "attribute_fx_original_amount")
  private String fxOriginalAmount;

  @Column (name = "attribute_fx_original_currency")
  private String fxOriginalCurrency;

  @Column (name = "attribute_numericReference")
  private String numericReference;

  @Column (name = "attribute_paymentId")
  private String paymentId;

  @Column (name = "attribute_paymentPurpose")
  private String paymentPurpose;

  @Column (name = "attribute_paymentScheme")
  private String paymentScheme;

  @Column (name = "attribute_paymentType")
  private String paymentType;

  @Column (name = "attribute_processingDate")
  private String processingDate;

  @Column (name = "attribute_reference")
  private String reference;

  @Column (name = "attribute_schemePaymentSubType")
  private String schemePaymentSubType;

  @Column (name = "attribute_schemePaymentType")
  private String schemePaymentType;

  @Column (name = "attribute_sponsorParty_accountNumber")
  private String sponsorPartyAccountNumber;

  @Column (name = "attribute_sponsorParty_bankId")
  private String sponsorPartyBankId;

  @Column (name = "attribute_sponsorParty_bankIdCode")
  private String sponsorPartyBankIdCode;

  private Payment(
      Builder builder) {
    this.id = builder.id;
    this.type = builder.type;
    this.version = builder.version;
    this.organisationId = builder.organisationId;
    this.amount = builder.amount;
    this.beneficiaryPartyAccountName = builder.beneficiaryPartyAccountName;
    this.beneficiaryPartyAccountNumber = builder.beneficiaryPartyAccountNumber;
    this.beneficiaryPartyAccountNumberCode = builder.beneficiaryPartyAccountNumberCode;
    this.beneficiaryPartyAccountType = builder.beneficiaryPartyAccountType;
    this.beneficiaryPartyAddress = builder.beneficiaryPartyAddress;
    this.beneficiaryPartyBankId = builder.beneficiaryPartyBankId;
    this.beneficiaryPartyBankIdCode = builder.beneficiaryPartyBankIdCode;
    this.beneficiaryPartyName = builder.beneficiaryPartyName;
    this.chargesInformationBearerCode = builder.chargesInformationBearerCode;
    this.chargesInformationSenderCharges = builder.chargesInformationSenderCharges;
    this.chargesInformationReceiverChargesAmount = builder.chargesInformationReceiverChargesAmount;
    this.chargesInformationReceiverChargesCurrency = builder.chargesInformationReceiverChargesCurrency;
    this.currency = builder.currency;
    this.debtorPartyAccountName = builder.debtorPartyAccountName;
    this.debtorPartyAccountNumber = builder.debtorPartyAccountNumber;
    this.debtorPartyAccountNumberCode = builder.debtorPartyAccountNumberCode;
    this.debtorPartyAddress = builder.debtorPartyAddress;
    this.debtorPartyBankId = builder.debtorPartyBankId;
    this.debtorPartyBankIdCode = builder.debtorPartyBankIdCode;
    this.debtorPartyName = builder.debtorPartyName;
    this.endToEndReference = builder.endToEndReference;
    this.fxContractReference = builder.fxContractReference;
    this.fxExchangeRate = builder.fxExchangeRate;
    this.fxOriginalAmount = builder.fxOriginalAmount;
    this.fxOriginalCurrency = builder.fxOriginalCurrency;
    this.numericReference = builder.numericReference;
    this.paymentId = builder.paymentId;
    this.paymentPurpose = builder.paymentPurpose;
    this.paymentScheme = builder.paymentScheme;
    this.paymentType = builder.paymentType;
    this.processingDate = builder.processingDate;
    this.reference = builder.reference;
    this.schemePaymentSubType = builder.schemePaymentSubType;
    this.schemePaymentType = builder.schemePaymentType;
    this.sponsorPartyAccountNumber = builder.sponsorPartyAccountNumber;
    this.sponsorPartyBankId = builder.sponsorPartyBankId;
    this.sponsorPartyBankIdCode = builder.sponsorPartyBankIdCode;
  }

  public Payment() {
    super();
  }

  public String getId() {
    return id;
  }

  public PaymentType getType() {
    return type;
  }

  public Integer getVersion() {
    return version;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public String getAmount() {
    return amount;
  }

  public String getBeneficiaryPartyAccountName() {
    return beneficiaryPartyAccountName;
  }

  public String getBeneficiaryPartyAccountNumber() {
    return beneficiaryPartyAccountNumber;
  }

  public String getBeneficiaryPartyAccountNumberCode() {
    return beneficiaryPartyAccountNumberCode;
  }

  public Integer getBeneficiaryPartyAccountType() {
    return beneficiaryPartyAccountType;
  }

  public String getBeneficiaryPartyAddress() {
    return beneficiaryPartyAddress;
  }

  public String getBeneficiaryPartyBankId() {
    return beneficiaryPartyBankId;
  }

  public String getBeneficiaryPartyBankIdCode() {
    return beneficiaryPartyBankIdCode;
  }

  public String getBeneficiaryPartyName() {
    return beneficiaryPartyName;
  }

  public String getChargesInformationBearerCode() {
    return chargesInformationBearerCode;
  }

  public Set<SenderCharge> getChargesInformationSenderCharges() {
    return chargesInformationSenderCharges;
  }

  public String getChargesInformationReceiverChargesAmount() {
    return chargesInformationReceiverChargesAmount;
  }

  public String getChargesInformationReceiverChargesCurrency() {
    return chargesInformationReceiverChargesCurrency;
  }

  public String getCurrency() {
    return currency;
  }

  public String getDebtorPartyAccountName() {
    return debtorPartyAccountName;
  }

  public String getDebtorPartyAccountNumber() {
    return debtorPartyAccountNumber;
  }

  public String getDebtorPartyAccountNumberCode() {
    return debtorPartyAccountNumberCode;
  }

  public String getDebtorPartyAddress() {
    return debtorPartyAddress;
  }

  public String getDebtorPartyBankId() {
    return debtorPartyBankId;
  }

  public String getDebtorPartyBankIdCode() {
    return debtorPartyBankIdCode;
  }

  public String getDebtorPartyName() {
    return debtorPartyName;
  }

  public String getEndToEndReference() {
    return endToEndReference;
  }

  public String getFxContractReference() {
    return fxContractReference;
  }

  public String getFxExchangeRate() {
    return fxExchangeRate;
  }

  public String getFxOriginalAmount() {
    return fxOriginalAmount;
  }

  public String getFxOriginalCurrency() {
    return fxOriginalCurrency;
  }

  public String getNumericReference() {
    return numericReference;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public String getPaymentPurpose() {
    return paymentPurpose;
  }

  public String getPaymentScheme() {
    return paymentScheme;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public String getProcessingDate() {
    return processingDate;
  }

  public String getReference() {
    return reference;
  }

  public String getSchemePaymentSubType() {
    return schemePaymentSubType;
  }

  public String getSchemePaymentType() {
    return schemePaymentType;
  }

  public String getSponsorPartyAccountNumber() {
    return sponsorPartyAccountNumber;
  }

  public String getSponsorPartyBankId() {
    return sponsorPartyBankId;
  }

  public String getSponsorPartyBankIdCode() {
    return sponsorPartyBankIdCode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Payment other = (Payment) obj;
    return Objects.equals(id, other.id);
  }

  /**
   * Creates builder to build {@link Payment}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link Payment}.
   */
  public static final class Builder {
    private String id;
    private PaymentType type;
    private Integer version;
    private String organisationId;
    private String amount;
    private String beneficiaryPartyAccountName;
    private String beneficiaryPartyAccountNumber;
    private String beneficiaryPartyAccountNumberCode;
    private Integer beneficiaryPartyAccountType;
    private String beneficiaryPartyAddress;
    private String beneficiaryPartyBankId;
    private String beneficiaryPartyBankIdCode;
    private String beneficiaryPartyName;
    private String chargesInformationBearerCode;
    private Set<SenderCharge> chargesInformationSenderCharges = Collections.emptySet();
    private String chargesInformationReceiverChargesAmount;
    private String chargesInformationReceiverChargesCurrency;
    private String currency;
    private String debtorPartyAccountName;
    private String debtorPartyAccountNumber;
    private String debtorPartyAccountNumberCode;
    private String debtorPartyAddress;
    private String debtorPartyBankId;
    private String debtorPartyBankIdCode;
    private String debtorPartyName;
    private String endToEndReference;
    private String fxContractReference;
    private String fxExchangeRate;
    private String fxOriginalAmount;
    private String fxOriginalCurrency;
    private String numericReference;
    private String paymentId;
    private String paymentPurpose;
    private String paymentScheme;
    private String paymentType;
    private String processingDate;
    private String reference;
    private String schemePaymentSubType;
    private String schemePaymentType;
    private String sponsorPartyAccountNumber;
    private String sponsorPartyBankId;
    private String sponsorPartyBankIdCode;

    private Builder() {
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withType(PaymentType type) {
      this.type = type;
      return this;
    }

    public Builder withVersion(Integer version) {
      this.version = version;
      return this;
    }

    public Builder withOrganisationId(String organisationId) {
      this.organisationId = organisationId;
      return this;
    }

    public Builder withAmount(String amount) {
      this.amount = amount;
      return this;
    }

    public Builder withBeneficiaryPartyAccountName(String beneficiaryPartyAccountName) {
      this.beneficiaryPartyAccountName = beneficiaryPartyAccountName;
      return this;
    }

    public Builder withBeneficiaryPartyAccountNumber(String beneficiaryPartyAccountNumber) {
      this.beneficiaryPartyAccountNumber = beneficiaryPartyAccountNumber;
      return this;
    }

    public Builder withBeneficiaryPartyAccountNumberCode(String beneficiaryPartyAccountNumberCode) {
      this.beneficiaryPartyAccountNumberCode = beneficiaryPartyAccountNumberCode;
      return this;
    }

    public Builder withBeneficiaryPartyAccountType(Integer beneficiaryPartyAccountType) {
      this.beneficiaryPartyAccountType = beneficiaryPartyAccountType;
      return this;
    }

    public Builder withBeneficiaryPartyAddress(String beneficiaryPartyAddress) {
      this.beneficiaryPartyAddress = beneficiaryPartyAddress;
      return this;
    }

    public Builder withBeneficiaryPartyBankId(String beneficiaryPartyBankId) {
      this.beneficiaryPartyBankId = beneficiaryPartyBankId;
      return this;
    }

    public Builder withBeneficiaryPartyBankIdCode(String beneficiaryPartyBankIdCode) {
      this.beneficiaryPartyBankIdCode = beneficiaryPartyBankIdCode;
      return this;
    }

    public Builder withBeneficiaryPartyName(String beneficiaryPartyName) {
      this.beneficiaryPartyName = beneficiaryPartyName;
      return this;
    }

    public Builder withChargesInformationBearerCode(String chargesInformationBearerCode) {
      this.chargesInformationBearerCode = chargesInformationBearerCode;
      return this;
    }

    public Builder withChargesInformationSenderCharges(Set<SenderCharge> chargesInformationSenderCharges) {
      this.chargesInformationSenderCharges = chargesInformationSenderCharges;
      return this;
    }

    public Builder withChargesInformationReceiverChargesAmount(String chargesInformationReceiverChargesAmount) {
      this.chargesInformationReceiverChargesAmount = chargesInformationReceiverChargesAmount;
      return this;
    }

    public Builder withChargesInformationReceiverChargesCurrency(String chargesInformationReceiverChargesCurrency) {
      this.chargesInformationReceiverChargesCurrency = chargesInformationReceiverChargesCurrency;
      return this;
    }

    public Builder withCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    public Builder withDebtorPartyAccountName(String debtorPartyAccountName) {
      this.debtorPartyAccountName = debtorPartyAccountName;
      return this;
    }

    public Builder withDebtorPartyAccountNumber(String debtorPartyAccountNumber) {
      this.debtorPartyAccountNumber = debtorPartyAccountNumber;
      return this;
    }

    public Builder withDebtorPartyAccountNumberCode(String debtorPartyAccountNumberCode) {
      this.debtorPartyAccountNumberCode = debtorPartyAccountNumberCode;
      return this;
    }

    public Builder withDebtorPartyAddress(String debtorPartyAddress) {
      this.debtorPartyAddress = debtorPartyAddress;
      return this;
    }

    public Builder withDebtorPartyBankId(String debtorPartyBankId) {
      this.debtorPartyBankId = debtorPartyBankId;
      return this;
    }

    public Builder withDebtorPartyBankIdCode(String debtorPartyBankIdCode) {
      this.debtorPartyBankIdCode = debtorPartyBankIdCode;
      return this;
    }

    public Builder withDebtorPartyName(String debtorPartyName) {
      this.debtorPartyName = debtorPartyName;
      return this;
    }

    public Builder withEndToEndReference(String endToEndReference) {
      this.endToEndReference = endToEndReference;
      return this;
    }

    public Builder withFxContractReference(String fxContractReference) {
      this.fxContractReference = fxContractReference;
      return this;
    }

    public Builder withFxExchangeRate(String fxExchangeRate) {
      this.fxExchangeRate = fxExchangeRate;
      return this;
    }

    public Builder withFxOriginalAmount(String fxOriginalAmount) {
      this.fxOriginalAmount = fxOriginalAmount;
      return this;
    }

    public Builder withFxOriginalCurrency(String fxOriginalCurrency) {
      this.fxOriginalCurrency = fxOriginalCurrency;
      return this;
    }

    public Builder withNumericReference(String numericReference) {
      this.numericReference = numericReference;
      return this;
    }

    public Builder withPaymentId(String paymentId) {
      this.paymentId = paymentId;
      return this;
    }

    public Builder withPaymentPurpose(String paymentPurpose) {
      this.paymentPurpose = paymentPurpose;
      return this;
    }

    public Builder withPaymentScheme(String paymentScheme) {
      this.paymentScheme = paymentScheme;
      return this;
    }

    public Builder withPaymentType(String paymentType) {
      this.paymentType = paymentType;
      return this;
    }

    public Builder withProcessingDate(String processingDate) {
      this.processingDate = processingDate;
      return this;
    }

    public Builder withReference(String reference) {
      this.reference = reference;
      return this;
    }

    public Builder withSchemePaymentSubType(String schemePaymentSubType) {
      this.schemePaymentSubType = schemePaymentSubType;
      return this;
    }

    public Builder withSchemePaymentType(String schemePaymentType) {
      this.schemePaymentType = schemePaymentType;
      return this;
    }

    public Builder withSponsorPartyAccountNumber(String sponsorPartyAccountNumber) {
      this.sponsorPartyAccountNumber = sponsorPartyAccountNumber;
      return this;
    }

    public Builder withSponsorPartyBankId(String sponsorPartyBankId) {
      this.sponsorPartyBankId = sponsorPartyBankId;
      return this;
    }

    public Builder withSponsorPartyBankIdCode(String sponsorPartyBankIdCode) {
      this.sponsorPartyBankIdCode = sponsorPartyBankIdCode;
      return this;
    }

    public Payment build() {
      return new Payment(this);
    }
  }

}
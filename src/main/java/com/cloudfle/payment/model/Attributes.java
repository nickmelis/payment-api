package com.cloudfle.payment.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = Attributes.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "amount",
  "beneficiary_party",
  "charges_information",
  "currency",
  "debtor_party",
  "end_to_end_reference",
  "fx",
  "numeric_reference",
  "payment_id",
  "payment_purpose",
  "payment_scheme",
  "payment_type",
  "processing_date",
  "reference",
  "scheme_payment_sub_type",
  "scheme_payment_type",
  "sponsor_party"
})
public class Attributes {

  @JsonProperty ("amount")
  private final String amount;
  @JsonProperty ("beneficiary_party")
  private final BeneficiaryParty beneficiaryParty;
  @JsonProperty ("charges_information")
  private final ChargesInformation chargesInformation;
  @JsonProperty ("currency")
  private final String currency;
  @JsonProperty ("debtor_party")
  private final DebtorParty debtorParty;
  @JsonProperty ("end_to_end_reference")
  private final String endToEndReference;
  @JsonProperty ("fx")
  private final Fx fx;
  @JsonProperty ("numeric_reference")
  private final String numericReference;
  @JsonProperty ("payment_id")
  private final String paymentId;
  @JsonProperty ("payment_purpose")
  private final String paymentPurpose;
  @JsonProperty ("payment_scheme")
  private final String paymentScheme;
  @JsonProperty ("payment_type")
  private final String paymentType;
  @JsonProperty ("processing_date")
  private final String processingDate;
  @JsonProperty ("reference")
  private final String reference;
  @JsonProperty ("scheme_payment_sub_type")
  private final String schemePaymentSubType;
  @JsonProperty ("scheme_payment_type")
  private final String schemePaymentType;
  @JsonProperty ("sponsor_party")
  private final SponsorParty sponsorParty;

  private Attributes(
      Builder builder) {
    this.amount = builder.amount;
    this.beneficiaryParty = builder.beneficiaryParty;
    this.chargesInformation = builder.chargesInformation;
    this.currency = builder.currency;
    this.debtorParty = builder.debtorParty;
    this.endToEndReference = builder.endToEndReference;
    this.fx = builder.fx;
    this.numericReference = builder.numericReference;
    this.paymentId = builder.paymentId;
    this.paymentPurpose = builder.paymentPurpose;
    this.paymentScheme = builder.paymentScheme;
    this.paymentType = builder.paymentType;
    this.processingDate = builder.processingDate;
    this.reference = builder.reference;
    this.schemePaymentSubType = builder.schemePaymentSubType;
    this.schemePaymentType = builder.schemePaymentType;
    this.sponsorParty = builder.sponsorParty;
  }

  public String getAmount() {
    return amount;
  }

  public BeneficiaryParty getBeneficiaryParty() {
    return beneficiaryParty;
  }

  public ChargesInformation getChargesInformation() {
    return chargesInformation;
  }

  public String getCurrency() {
    return currency;
  }

  public DebtorParty getDebtorParty() {
    return debtorParty;
  }

  public String getEndToEndReference() {
    return endToEndReference;
  }

  public Fx getFx() {
    return fx;
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

  public SponsorParty getSponsorParty() {
    return sponsorParty;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, beneficiaryParty, chargesInformation, currency, debtorParty, endToEndReference, fx,
      numericReference, paymentId, paymentPurpose, paymentScheme, paymentType, processingDate, reference,
      schemePaymentSubType, schemePaymentType, sponsorParty);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Attributes other = (Attributes) obj;
    return Objects.equals(amount, other.amount) && Objects.equals(beneficiaryParty, other.beneficiaryParty)
      && Objects.equals(chargesInformation, other.chargesInformation) && Objects.equals(currency, other.currency)
      && Objects.equals(debtorParty, other.debtorParty) && Objects.equals(endToEndReference, other.endToEndReference)
      && Objects.equals(fx, other.fx) && Objects.equals(numericReference, other.numericReference)
      && Objects.equals(paymentId, other.paymentId) && Objects.equals(paymentPurpose, other.paymentPurpose)
      && Objects.equals(paymentScheme, other.paymentScheme) && Objects.equals(paymentType, other.paymentType)
      && Objects.equals(processingDate, other.processingDate) && Objects.equals(reference, other.reference)
      && Objects.equals(schemePaymentSubType, other.schemePaymentSubType)
      && Objects.equals(schemePaymentType, other.schemePaymentType) && Objects.equals(sponsorParty, other.sponsorParty);
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append("Attributes [amount=").append(amount)
      .append(", beneficiaryParty=").append(beneficiaryParty)
      .append(", chargesInformation=").append(chargesInformation)
      .append(", currency=").append(currency)
      .append(", debtorParty=").append(debtorParty)
      .append(", endToEndReference=").append(endToEndReference)
      .append(", fx=").append(fx)
      .append(", numericReference=").append(numericReference)
      .append(", paymentId=").append(paymentId)
      .append(", paymentPurpose=").append(paymentPurpose)
      .append(", paymentScheme=").append(paymentScheme)
      .append(", paymentType=").append(paymentType)
      .append(", processingDate=").append(processingDate)
      .append(", reference=").append(reference)
      .append(", schemePaymentSubType=").append(schemePaymentSubType)
      .append(", schemePaymentType=").append(schemePaymentType)
      .append(", sponsorParty=").append(sponsorParty)
      .append("]")
      .toString();
  }

  /**
   * Creates builder to build {@link Attributes}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link Attributes}.
   */
  public static final class Builder {
    @JsonProperty ("amount")
    private String amount;
    @JsonProperty ("beneficiary_party")
    private BeneficiaryParty beneficiaryParty;
    @JsonProperty ("charges_information")
    private ChargesInformation chargesInformation;
    @JsonProperty ("currency")
    private String currency;
    @JsonProperty ("debtor_party")
    private DebtorParty debtorParty;
    @JsonProperty ("end_to_end_reference")
    private String endToEndReference;
    @JsonProperty ("fx")
    private Fx fx;
    @JsonProperty ("numeric_reference")
    private String numericReference;
    @JsonProperty ("payment_id")
    private String paymentId;
    @JsonProperty ("payment_purpose")
    private String paymentPurpose;
    @JsonProperty ("payment_scheme")
    private String paymentScheme;
    @JsonProperty ("payment_type")
    private String paymentType;
    @JsonProperty ("processing_date")
    private String processingDate;
    @JsonProperty ("reference")
    private String reference;
    @JsonProperty ("scheme_payment_sub_type")
    private String schemePaymentSubType;
    @JsonProperty ("scheme_payment_type")
    private String schemePaymentType;
    @JsonProperty ("sponsor_party")
    private SponsorParty sponsorParty;

    private Builder() {
    }

    public Builder withAmount(String amount) {
      this.amount = amount;
      return this;
    }

    public Builder withBeneficiaryParty(BeneficiaryParty beneficiaryParty) {
      this.beneficiaryParty = beneficiaryParty;
      return this;
    }

    public Builder withChargesInformation(ChargesInformation chargesInformation) {
      this.chargesInformation = chargesInformation;
      return this;
    }

    public Builder withCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    public Builder withDebtorParty(DebtorParty debtorParty) {
      this.debtorParty = debtorParty;
      return this;
    }

    public Builder withEndToEndReference(String endToEndReference) {
      this.endToEndReference = endToEndReference;
      return this;
    }

    public Builder withFx(Fx fx) {
      this.fx = fx;
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

    public Builder withSponsorParty(SponsorParty sponsorParty) {
      this.sponsorParty = sponsorParty;
      return this;
    }

    public Attributes build() {
      return new Attributes(this);
    }
  }
}
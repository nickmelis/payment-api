package com.cloudfle.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = SponsorParty.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "account_number",
  "bank_id",
  "bank_id_code"
})
public class SponsorParty {

  @JsonProperty ("account_number")
  private final String accountNumber;
  @JsonProperty ("bank_id")
  private final String bankId;
  @JsonProperty ("bank_id_code")
  private final String bankIdCode;

  private SponsorParty(
      Builder builder) {
    this.accountNumber = builder.accountNumber;
    this.bankId = builder.bankId;
    this.bankIdCode = builder.bankIdCode;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getBankId() {
    return bankId;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }

  /**
   * Creates builder to build {@link SponsorParty}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link SponsorParty}.
   */
  public static final class Builder {
    @JsonProperty ("account_number")
    private String accountNumber;
    @JsonProperty ("bank_id")
    private String bankId;
    @JsonProperty ("bank_id_code")
    private String bankIdCode;

    private Builder() {
    }

    public Builder withAccountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
      return this;
    }

    public Builder withBankId(String bankId) {
      this.bankId = bankId;
      return this;
    }

    public Builder withBankIdCode(String bankIdCode) {
      this.bankIdCode = bankIdCode;
      return this;
    }

    public SponsorParty build() {
      return new SponsorParty(this);
    }
  }
}
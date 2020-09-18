package com.cloudfle.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = Fx.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "contract_reference",
  "exchange_rate",
  "original_amount",
  "original_currency"
})
public class Fx {

  @JsonProperty ("contract_reference")
  private final String contractReference;
  @JsonProperty ("exchange_rate")
  private final String exchangeRate;
  @JsonProperty ("original_amount")
  private final String originalAmount;
  @JsonProperty ("original_currency")
  private final String originalCurrency;

  private Fx(
      Builder builder) {
    this.contractReference = builder.contractReference;
    this.exchangeRate = builder.exchangeRate;
    this.originalAmount = builder.originalAmount;
    this.originalCurrency = builder.originalCurrency;
  }

  public String getContractReference() {
    return contractReference;
  }

  public String getExchangeRate() {
    return exchangeRate;
  }

  public String getOriginalAmount() {
    return originalAmount;
  }

  public String getOriginalCurrency() {
    return originalCurrency;
  }

  /**
   * Creates builder to build {@link Fx}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link Fx}.
   */
  public static final class Builder {
    @JsonProperty ("contract_reference")
    private String contractReference;
    @JsonProperty ("exchange_rate")
    private String exchangeRate;
    @JsonProperty ("original_amount")
    private String originalAmount;
    @JsonProperty ("original_currency")
    private String originalCurrency;

    private Builder() {
    }

    public Builder withContractReference(String contractReference) {
      this.contractReference = contractReference;
      return this;
    }

    public Builder withExchangeRate(String exchangeRate) {
      this.exchangeRate = exchangeRate;
      return this;
    }

    public Builder withOriginalAmount(String originalAmount) {
      this.originalAmount = originalAmount;
      return this;
    }

    public Builder withOriginalCurrency(String originalCurrency) {
      this.originalCurrency = originalCurrency;
      return this;
    }

    public Fx build() {
      return new Fx(this);
    }
  }
}
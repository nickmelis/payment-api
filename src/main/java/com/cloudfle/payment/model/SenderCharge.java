package com.cloudfle.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize (builder = SenderCharge.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "amount",
  "currency"
})
public class SenderCharge {

  @JsonProperty ("amount")
  private final String amount;
  @JsonProperty ("currency")
  private final String currency;

  private SenderCharge(
      Builder builder) {
    this.amount = builder.amount;
    this.currency = builder.currency;
  }

  public String getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  /**
   * Creates builder to build {@link SenderCharge}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link SenderCharge}.
   */
  @JsonPOJOBuilder
  public static final class Builder {
    @JsonProperty ("amount")
    private String amount;
    @JsonProperty ("currency")
    private String currency;


    private Builder() {
    }

    public Builder withAmount(String amount) {
      this.amount = amount;
      return this;
    }

    public Builder withCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    public SenderCharge build() {
      return new SenderCharge(this);
    }
  }
}
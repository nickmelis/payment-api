package tech.form3.payment.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = ChargesInformation.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "bearer_code",
  "sender_charges",
  "receiver_charges_amount",
  "receiver_charges_currency"
})
public class ChargesInformation {

  @JsonProperty ("bearer_code")
  private final String bearerCode;
  @JsonProperty ("sender_charges")
  private final List<SenderCharge> senderCharges;
  @JsonProperty ("receiver_charges_amount")
  private final String receiverChargesAmount;
  @JsonProperty ("receiver_charges_currency")
  private final String receiverChargesCurrency;

  private ChargesInformation(
      Builder builder) {
    this.bearerCode = builder.bearerCode;
    this.senderCharges = builder.senderCharges;
    this.receiverChargesAmount = builder.receiverChargesAmount;
    this.receiverChargesCurrency = builder.receiverChargesCurrency;
  }

  public String getBearerCode() {
    return bearerCode;
  }

  public List<SenderCharge> getSenderCharges() {
    return senderCharges;
  }

  public String getReceiverChargesAmount() {
    return receiverChargesAmount;
  }

  public String getReceiverChargesCurrency() {
    return receiverChargesCurrency;
  }

  /**
   * Creates builder to build {@link ChargesInformation}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link ChargesInformation}.
   */
  public static final class Builder {
    @JsonProperty ("bearer_code")
    private String bearerCode;
    @JsonProperty ("sender_charges")
    private List<SenderCharge> senderCharges = new ArrayList<>();
    @JsonProperty ("receiver_charges_amount")
    private String receiverChargesAmount;
    @JsonProperty ("receiver_charges_currency")
    private String receiverChargesCurrency;

    private Builder() {
    }

    public Builder withBearerCode(String bearerCode) {
      this.bearerCode = bearerCode;
      return this;
    }

    public Builder withSenderCharges(List<SenderCharge> senderCharges) {
      this.senderCharges = senderCharges;
      return this;
    }

    public Builder withSenderCharge(SenderCharge senderCharge) {
      this.senderCharges.add(senderCharge);
      return this;
    }

    public Builder withReceiverChargesAmount(String receiverChargesAmount) {
      this.receiverChargesAmount = receiverChargesAmount;
      return this;
    }

    public Builder withReceiverChargesCurrency(String receiverChargesCurrency) {
      this.receiverChargesCurrency = receiverChargesCurrency;
      return this;
    }

    public ChargesInformation build() {
      return new ChargesInformation(this);
    }
  }
}
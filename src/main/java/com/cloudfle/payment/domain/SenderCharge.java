package com.cloudfle.payment.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "sender_charges")
public class SenderCharge {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @Column (name = "amount")
  private String amount;

  @Column (name = "currency")
  private String currency;

  @Column (name = "payment_id")
  private String paymentId;

  private SenderCharge(
      Builder builder) {
    this.amount = builder.amount;
    this.currency = builder.currency;
    this.paymentId = builder.paymentId;
  }

  public SenderCharge() {
    super();
  }

  public String getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getPaymentId() {
    return paymentId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency, paymentId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SenderCharge other = (SenderCharge) obj;
    return Objects.equals(amount, other.amount) && Objects.equals(currency, other.currency)
      && Objects.equals(paymentId, other.paymentId);
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
  public static final class Builder {
    private String amount;
    private String currency;
    private String paymentId;

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

    public Builder withPaymentId(String paymentId) {
      this.paymentId = paymentId;
      return this;
    }

    public SenderCharge build() {
      return new SenderCharge(this);
    }
  }
}
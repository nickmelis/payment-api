package com.cloudfle.payment.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = Payment.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "type",
  "id",
  "version",
  "organisation_id",
  "attributes"
})
public class Payment {

  @JsonProperty ("type")
  @NotNull (message = "Payment type is mandatory")
  private final PaymentType type;

  @JsonProperty ("id")
  @NotBlank (message = "Payment id is mandatory")
  private final String id;

  @JsonProperty ("version")
  @NotNull (message = "Payment version is mandatory")
  private final Integer version;

  @NotBlank (message = "Organisation id is mandatory")
  @JsonProperty ("organisation_id")
  private final String organisationId;

  @JsonProperty ("attributes")
  private final Attributes attributes;

  private Payment(
      Builder builder) {
    this.type = builder.type;
    this.id = builder.id;
    this.version = builder.version;
    this.organisationId = builder.organisationId;
    this.attributes = builder.attributes;
  }

  public PaymentType getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public Integer getVersion() {
    return version;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public Attributes getAttributes() {
    return attributes;
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributes, id, organisationId, type, version);
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
    return Objects.equals(attributes, other.attributes) && Objects.equals(id, other.id)
      && Objects.equals(organisationId, other.organisationId) && type == other.type
      && Objects.equals(version, other.version);
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append("Payment [type=").append(type)
      .append(", id=").append(id)
      .append(", version=").append(version)
      .append(", organisationId=").append(organisationId)
      .append(", attributes=").append(attributes)
      .append("]")
      .toString();
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
    @JsonProperty ("type")
    private PaymentType type;
    @JsonProperty ("id")
    private String id;
    @JsonProperty ("version")
    private Integer version;
    @JsonProperty ("organisation_id")
    private String organisationId;
    @JsonProperty ("attributes")
    private Attributes attributes;

    private Builder() {
    }

    public Builder withType(PaymentType type) {
      this.type = type;
      return this;
    }

    public Builder withId(String id) {
      this.id = id;
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

    public Builder withAttributes(Attributes attributes) {
      this.attributes = attributes;
      return this;
    }

    public Payment build() {
      return new Payment(this);
    }
  }
}
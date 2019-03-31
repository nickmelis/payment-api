package tech.form3.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = BeneficiaryParty.Builder.class)
@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
  "account_name",
  "account_number",
  "account_number_code",
  "account_type",
  "address",
  "bank_id",
  "bank_id_code",
  "name"
})
public class BeneficiaryParty {

  @JsonProperty ("account_name")
  private final String accountName;
  @JsonProperty ("account_number")
  private final String accountNumber;
  @JsonProperty ("account_number_code")
  private final String accountNumberCode;
  @JsonProperty ("account_type")
  private final Integer accountType;
  @JsonProperty ("address")
  private final String address;
  @JsonProperty ("bank_id")
  private final String bankId;
  @JsonProperty ("bank_id_code")
  private final String bankIdCode;
  @JsonProperty ("name")
  private final String name;

  private BeneficiaryParty(
      Builder builder) {
    this.accountName = builder.accountName;
    this.accountNumber = builder.accountNumber;
    this.accountNumberCode = builder.accountNumberCode;
    this.accountType = builder.accountType;
    this.address = builder.address;
    this.bankId = builder.bankId;
    this.bankIdCode = builder.bankIdCode;
    this.name = builder.name;
  }

  public String getAccountName() {
    return accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountNumberCode() {
    return accountNumberCode;
  }

  public Integer getAccountType() {
    return accountType;
  }

  public String getAddress() {
    return address;
  }

  public String getBankId() {
    return bankId;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }

  public String getName() {
    return name;
  }

  /**
   * Creates builder to build {@link BeneficiaryParty}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link BeneficiaryParty}.
   */
  public static final class Builder {
    @JsonProperty ("account_name")
    private String accountName;
    @JsonProperty ("account_number")
    private String accountNumber;
    @JsonProperty ("account_number_code")
    private String accountNumberCode;
    @JsonProperty ("account_type")
    private Integer accountType;
    @JsonProperty ("address")
    private String address;
    @JsonProperty ("bank_id")
    private String bankId;
    @JsonProperty ("bank_id_code")
    private String bankIdCode;
    @JsonProperty ("name")
    private String name;

    private Builder() {
    }

    public Builder withAccountName(String accountName) {
      this.accountName = accountName;
      return this;
    }

    public Builder withAccountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
      return this;
    }

    public Builder withAccountNumberCode(String accountNumberCode) {
      this.accountNumberCode = accountNumberCode;
      return this;
    }

    public Builder withAccountType(Integer accountType) {
      this.accountType = accountType;
      return this;
    }

    public Builder withAddress(String address) {
      this.address = address;
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

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public BeneficiaryParty build() {
      return new BeneficiaryParty(this);
    }
  }
}
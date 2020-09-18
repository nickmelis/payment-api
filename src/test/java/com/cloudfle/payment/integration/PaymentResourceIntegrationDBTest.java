package com.cloudfle.payment.integration;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cloudfle.payment.model.Attributes;
import com.cloudfle.payment.model.BeneficiaryParty;
import com.cloudfle.payment.model.ChargesInformation;
import com.cloudfle.payment.model.DebtorParty;
import com.cloudfle.payment.model.Fx;
import com.cloudfle.payment.model.Payment;
import com.cloudfle.payment.model.PaymentType;
import com.cloudfle.payment.model.SenderCharge;
import com.cloudfle.payment.model.SponsorParty;

/**
 * This test runs the application against an embedded H2 database.
 * It checks basic CRUD functionality, like insert, update, delete and get.
 * It also checks, field by field, that what we save is exactly what we get back afterwards.
 */
@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext (classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PaymentResourceIntegrationDBTest {

  private static final String PAYMENT_API = "/v1/payments";

  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private MockMvc mockMvc;

  /**
   * This tests aims at testing the consistency model-domain mappings on the way to the DAO and back
   * It executes a basic create and fetch to verify all the fields are correctly mapped both ways.
   *
   * @throws Exception
   */
  @Test
  public void testMapping() throws Exception {
    Payment payment1 = Payment.builder()
      .withType(PaymentType.PAYMENT)
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .withAttributes(Attributes.builder()
        .withAmount("100.21")
        .withBeneficiaryParty(BeneficiaryParty.builder()
          .withAccountName("W Owens")
          .withAccountNumber("31926819")
          .withAccountNumberCode("BBAN")
          .withAccountType(0)
          .withAddress("1 The Beneficiary Localtown SE2")
          .withBankId("403000")
          .withBankIdCode("GBDSC")
          .withName("Wilfred Jeremiah Owens")
          .build())
        .withChargesInformation(ChargesInformation.builder()
          .withBearerCode("SHAR")
          .withSenderCharge(SenderCharge.builder()
            .withAmount("5.00")
            .withCurrency("GBP")
            .build())
          .withSenderCharge(SenderCharge.builder()
            .withAmount("10.00")
            .withCurrency("USD")
            .build())
          .withReceiverChargesAmount("1.00")
          .withReceiverChargesCurrency("USD")
          .build())
        .withCurrency("GBP")
        .withDebtorParty(DebtorParty.builder()
          .withAccountName("EJ Brown Black")
          .withAccountNumber("GB29XABC10161234567801")
          .withAccountNumberCode("IBAN")
          .withAddress("10 Debtor Crescent Sourcetown NE1")
          .withBankId("203301")
          .withBankIdCode("GBDSC")
          .withName("Emelia Jane Brown")
          .build())
        .withEndToEndReference("Wil piano Jan")
        .withFx(Fx.builder()
          .withContractReference("FX123")
          .withExchangeRate("2.00000")
          .withOriginalAmount("200.42")
          .withOriginalCurrency("USD")
          .build())
        .withNumericReference("1002001")
        .withPaymentId("123456789012345678")
        .withPaymentPurpose("Paying for goods/services")
        .withPaymentScheme("FPS")
        .withPaymentType("Credit")
        .withProcessingDate("2017-01-18")
        .withReference("Payment for Em's piano lessons")
        .withSchemePaymentSubType("InternetBanking")
        .withSchemePaymentType("ImmediatePayment")
        .withSponsorParty(SponsorParty.builder()
          .withAccountNumber("56781234")
          .withBankId("123123")
          .withBankIdCode("GBDSC")
          .build())
        .build())
      .build();

    // Create payment
    mockMvc.perform(post(PAYMENT_API)
      .content(mapper.writeValueAsString(payment1))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1.getOrganisationId())))
      .andExpect(jsonPath("$.data.attributes.amount", is("100.21")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_name", is("W Owens")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_number", is("31926819")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_number_code", is("BBAN")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_type", is(0)))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.address", is("1 The Beneficiary Localtown SE2")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.bank_id", is("403000")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.bank_id_code", is("GBDSC")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.name", is("Wilfred Jeremiah Owens")))
      .andExpect(jsonPath("$.data.attributes.charges_information.bearer_code", is("SHAR")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[0].amount", is("5.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[0].currency", is("GBP")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[1].amount", is("10.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[1].currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.charges_information.receiver_charges_amount", is("1.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.receiver_charges_currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.currency", is("GBP")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_name", is("EJ Brown Black")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_number", is("GB29XABC10161234567801")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_number_code", is("IBAN")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.address", is("10 Debtor Crescent Sourcetown NE1")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.bank_id", is("203301")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.bank_id_code", is("GBDSC")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.name", is("Emelia Jane Brown")))
      .andExpect(jsonPath("$.data.attributes.end_to_end_reference", is("Wil piano Jan")))
      .andExpect(jsonPath("$.data.attributes.fx.contract_reference", is("FX123")))
      .andExpect(jsonPath("$.data.attributes.fx.exchange_rate", is("2.00000")))
      .andExpect(jsonPath("$.data.attributes.fx.original_amount", is("200.42")))
      .andExpect(jsonPath("$.data.attributes.fx.original_currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.numeric_reference", is("1002001")))
      .andExpect(jsonPath("$.data.attributes.payment_id", is("123456789012345678")))
      .andExpect(jsonPath("$.data.attributes.payment_purpose", is("Paying for goods/services")))
      .andExpect(jsonPath("$.data.attributes.payment_scheme", is("FPS")))
      .andExpect(jsonPath("$.data.attributes.payment_type", is("Credit")))
      .andExpect(jsonPath("$.data.attributes.processing_date", is("2017-01-18")))
      .andExpect(jsonPath("$.data.attributes.reference", is("Payment for Em's piano lessons")))
      .andExpect(jsonPath("$.data.attributes.scheme_payment_sub_type", is("InternetBanking")))
      .andExpect(jsonPath("$.data.attributes.scheme_payment_type", is("ImmediatePayment")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.account_number", is("56781234")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.bank_id", is("123123")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.bank_id_code", is("GBDSC")))
      .andDo(print());

    // Get payment, verify payment has been created and all mappings are correct
    mockMvc.perform(get(PAYMENT_API + "/{paymentId}", payment1.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1.getOrganisationId())))
      .andExpect(jsonPath("$.data.attributes.amount", is("100.21")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_name", is("W Owens")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_number", is("31926819")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_number_code", is("BBAN")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.account_type", is(0)))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.address", is("1 The Beneficiary Localtown SE2")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.bank_id", is("403000")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.bank_id_code", is("GBDSC")))
      .andExpect(jsonPath("$.data.attributes.beneficiary_party.name", is("Wilfred Jeremiah Owens")))
      .andExpect(jsonPath("$.data.attributes.charges_information.bearer_code", is("SHAR")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[0].amount", is("10.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[0].currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[1].amount", is("5.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.sender_charges[1].currency", is("GBP")))
      .andExpect(jsonPath("$.data.attributes.charges_information.receiver_charges_amount", is("1.00")))
      .andExpect(jsonPath("$.data.attributes.charges_information.receiver_charges_currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.currency", is("GBP")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_name", is("EJ Brown Black")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_number", is("GB29XABC10161234567801")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.account_number_code", is("IBAN")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.address", is("10 Debtor Crescent Sourcetown NE1")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.bank_id", is("203301")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.bank_id_code", is("GBDSC")))
      .andExpect(jsonPath("$.data.attributes.debtor_party.name", is("Emelia Jane Brown")))
      .andExpect(jsonPath("$.data.attributes.end_to_end_reference", is("Wil piano Jan")))
      .andExpect(jsonPath("$.data.attributes.fx.contract_reference", is("FX123")))
      .andExpect(jsonPath("$.data.attributes.fx.exchange_rate", is("2.00000")))
      .andExpect(jsonPath("$.data.attributes.fx.original_amount", is("200.42")))
      .andExpect(jsonPath("$.data.attributes.fx.original_currency", is("USD")))
      .andExpect(jsonPath("$.data.attributes.numeric_reference", is("1002001")))
      .andExpect(jsonPath("$.data.attributes.payment_id", is("123456789012345678")))
      .andExpect(jsonPath("$.data.attributes.payment_purpose", is("Paying for goods/services")))
      .andExpect(jsonPath("$.data.attributes.payment_scheme", is("FPS")))
      .andExpect(jsonPath("$.data.attributes.payment_type", is("Credit")))
      .andExpect(jsonPath("$.data.attributes.processing_date", is("2017-01-18")))
      .andExpect(jsonPath("$.data.attributes.reference", is("Payment for Em's piano lessons")))
      .andExpect(jsonPath("$.data.attributes.scheme_payment_sub_type", is("InternetBanking")))
      .andExpect(jsonPath("$.data.attributes.scheme_payment_type", is("ImmediatePayment")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.account_number", is("56781234")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.bank_id", is("123123")))
      .andExpect(jsonPath("$.data.attributes.sponsor_party.bank_id_code", is("GBDSC")))
      .andDo(print());
  }

  /**
   * This test executes basic CRUD functionalities and tests for consistency in the results.
   * It uses a simplified version of a Payment object as the consistency of all the fields
   * is already tested in the test above.
   *
   * @throws Exception
   */
  @Test
  public void testCreateUpdateDelete() throws Exception {
    Payment payment1 = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    Payment payment1Update = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(1)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();

    // Create payment
    mockMvc.perform(post(PAYMENT_API)
      .content(mapper.writeValueAsString(payment1))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1.getVersion())))
      .andDo(print());

    // Get payment, verify payment has been created
    mockMvc.perform(get(PAYMENT_API + "/{paymentId}", payment1.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1.getOrganisationId())))
      .andDo(print());

    // Update payment
    mockMvc.perform(put(PAYMENT_API + "/{paymentId}", payment1Update.getId())
      .content(mapper.writeValueAsString(payment1Update))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1Update.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1Update.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1Update.getOrganisationId())))
      .andDo(print());

    // Get payment, verify payment has been updated
    mockMvc.perform(get(PAYMENT_API + "/{paymentId}", payment1Update.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1Update.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1Update.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1Update.getOrganisationId())))
      .andDo(print());

    // Delete payment
    mockMvc.perform(delete(PAYMENT_API + "/{paymentId}", payment1Update.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1Update.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1Update.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1Update.getOrganisationId())))
      .andDo(print());

    // Get payment, verify payment does no longer exist
    mockMvc.perform(get(PAYMENT_API + "/{paymentId}", payment1Update.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andDo(print());
  }

  /**
   * This test simulates the retrieval of a non existing payment and checks
   * that the correct HTTP status is returned
   *
   * @throws Exception
   */
  @Test
  public void testGetNonExistingPayment() throws Exception {
    // Get payment, this should fail as no payment has been created
    mockMvc.perform(get(PAYMENT_API + "/{paymentId}", "DOESNOTEXIST")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.errors",
        containsInAnyOrder("Payment with id DOESNOTEXIST not found")))
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andDo(print());
  }

  /**
   * This test simulates the creation of an already existing payment and checks
   * that the correct HTTP status is returned
   *
   * @throws Exception
   */
  @Test
  public void testCreatePaymentTwice() throws JsonProcessingException, Exception {
    Payment payment1 = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();

    // Create payment
    mockMvc.perform(post(PAYMENT_API)
      .content(mapper.writeValueAsString(payment1))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andExpect(jsonPath("$.data.id", is(payment1.getId())))
      .andExpect(jsonPath("$.data.type", is("Payment")))
      .andExpect(jsonPath("$.data.version", is(payment1.getVersion())))
      .andExpect(jsonPath("$.data.organisation_id", is(payment1.getOrganisationId())))
      .andDo(print());

    // Create payment for the second time - this should return 400
    mockMvc.perform(post(PAYMENT_API)
      .content(mapper.writeValueAsString(payment1))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.errors",
        containsInAnyOrder("Payment with id 4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43 already exists")))
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andDo(print());
  }

  /**
   * This test simulates the update of a non existing payment and checks
   * that the correct HTTP status is returned
   *
   * @throws Exception
   */
  @Test
  public void testUpdateNonExistingPayment() throws JsonProcessingException, Exception {
    Payment payment1Update = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(1)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();

    // Update payment - this should fail as the payment does not exist
    mockMvc.perform(put(PAYMENT_API + "/{paymentId}", payment1Update.getId())
      .content(mapper.writeValueAsString(payment1Update))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.errors",
        containsInAnyOrder("Payment with id 4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43 not found")))
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andDo(print());
  }

  /**
   * This test simulates the deletion of a non existing payment and checks
   * that the correct HTTP status is returned
   *
   * @throws Exception
   */
  @Test
  public void testDeleteNonExistingPayment() throws JsonProcessingException, Exception {
    Payment payment1 = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();

    // Delete payment - this should fail as the payment does not exist
    mockMvc.perform(delete(PAYMENT_API + "/{paymentId}", payment1.getId())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.errors",
        containsInAnyOrder("Payment with id 4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43 not found")))
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andDo(print());
  }

  /**
   * This test verifies that validation for mandatory fields works.
   *
   * @throws Exception
   */
  @Test
  public void testValidation() throws Exception {
    // Create payment without mandatory fields - should fail
    mockMvc.perform(post(PAYMENT_API)
      .content(mapper.writeValueAsString(Payment.builder().build()))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.errors", containsInAnyOrder(
        "Payment id is mandatory",
        "Payment type is mandatory",
        "Payment version is mandatory",
        "Organisation id is mandatory")))
      .andExpect(jsonPath("$.links.self", is("https://api.test.form3.tech/v1/payments")))
      .andDo(print());
  }
}
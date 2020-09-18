package com.cloudfle.payment.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cloudfle.payment.dao.PaymentDao;
import com.cloudfle.payment.exception.InvalidInputException;
import com.cloudfle.payment.exception.PaymentAlreadyExistentException;
import com.cloudfle.payment.exception.PaymentNotFoundException;
import com.cloudfle.payment.model.Payment;
import com.cloudfle.payment.model.PaymentType;
import com.cloudfle.payment.service.PaymentService;

@RunWith (MockitoJUnitRunner.class)
public class PaymentServiceTest {

  @Mock
  private PaymentDao paymentDao;
  @InjectMocks
  private PaymentService service;

  @Test
  public void testGetAll() {
    com.cloudfle.payment.domain.Payment payment1 = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment payment2 = com.cloudfle.payment.domain.Payment.builder()
      .withId("52611302-0758-4f69-aa15-c5f55ab7c3eb")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(1)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findAll()).thenReturn(Arrays.asList(payment1, payment2));

    // When
    List<Payment> result = service.getAll();

    // Then
    assertThat(result, hasSize(2));
    assertThat(result.get(0).getId(), is("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43"));
    assertThat(result.get(0).getType(), is(PaymentType.PAYMENT));
    assertThat(result.get(0).getVersion(), is(0));
    assertThat(result.get(0).getOrganisationId(), is("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb"));
    assertThat(result.get(1).getId(), is("52611302-0758-4f69-aa15-c5f55ab7c3eb"));
    assertThat(result.get(1).getType(), is(PaymentType.PAYMENT));
    assertThat(result.get(1).getVersion(), is(1));
    assertThat(result.get(1).getOrganisationId(), is("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb"));
  }

  @Test
  public void testGetAllReturnsEmptyList() {
    when(paymentDao.findAll()).thenReturn(Arrays.asList());

    // When
    List<Payment> result = service.getAll();

    // Then
    assertThat(result, hasSize(0));
  }

  @Test
  public void testGetById() throws PaymentNotFoundException {
    com.cloudfle.payment.domain.Payment payment = com.cloudfle.payment.domain.Payment.builder()
      .withId("52611302-0758-4f69-aa15-c5f55ab7c3eb")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(1)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById("52611302-0758-4f69-aa15-c5f55ab7c3eb")).thenReturn(Optional.of(payment));

    // When
    Payment result = service.getById("52611302-0758-4f69-aa15-c5f55ab7c3eb");

    // Then
    assertThat(result, notNullValue());
    assertThat(result.getId(), is("52611302-0758-4f69-aa15-c5f55ab7c3eb"));
    assertThat(result.getType(), is(PaymentType.PAYMENT));
    assertThat(result.getVersion(), is(1));
    assertThat(result.getOrganisationId(), is("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb"));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testGetByNonExistingId() throws PaymentNotFoundException {
    when(paymentDao.findById("NONEXISTENT")).thenReturn(Optional.empty());

    // When
    service.getById("NONEXISTENT");
  }

  @Test
  public void testCreate() throws PaymentAlreadyExistentException {
    Payment newPayment = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment domainPayment = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(newPayment.getId())).thenReturn(Optional.empty());
    when(paymentDao.save(domainPayment)).thenReturn(domainPayment);

    // When
    Payment result = service.create(newPayment);

    // Then
    assertThat(result, notNullValue());
    assertThat(result.getId(), is("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43"));
    assertThat(result.getType(), is(PaymentType.PAYMENT));
    assertThat(result.getVersion(), is(0));
    assertThat(result.getOrganisationId(), is("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb"));

  }

  @Test (expected = PaymentAlreadyExistentException.class)
  public void testCreateExistingPayment() throws PaymentAlreadyExistentException {
    Payment newPayment = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment domainPayment = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(newPayment.getId())).thenReturn(Optional.of(domainPayment));

    service.create(newPayment);
  }

  @Test
  public void testUpdate() throws PaymentNotFoundException, InvalidInputException {
    Payment updatedPayment = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(5)
      .withOrganisationId("00000000-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment domainPayment = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment domainUpdatedPayment = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(5)
      .withOrganisationId("00000000-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(updatedPayment.getId())).thenReturn(Optional.of(domainPayment));
    when(paymentDao.save(domainUpdatedPayment)).thenReturn(domainUpdatedPayment);

    // When
    Payment result = service.update(updatedPayment.getId(), updatedPayment);

    // Then
    assertThat(result, notNullValue());
    assertThat(result.getId(), is("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43"));
    assertThat(result.getType(), is(PaymentType.PAYMENT));
    assertThat(result.getVersion(), is(5));
    assertThat(result.getOrganisationId(), is("00000000-8e6f-432e-a8fa-c5d8d2ee5fcb"));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testUpdateNonExistingRecord() throws PaymentNotFoundException, InvalidInputException {
    Payment updatedPayment = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(5)
      .withOrganisationId("00000000-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(updatedPayment.getId())).thenReturn(Optional.empty());

    service.update(updatedPayment.getId(), updatedPayment);
  }

  @Test
  public void testDelete() throws PaymentNotFoundException {
    Payment paymentToDelete = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    com.cloudfle.payment.domain.Payment domainPayment = com.cloudfle.payment.domain.Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(com.cloudfle.payment.domain.PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(paymentToDelete.getId())).thenReturn(Optional.of(domainPayment));

    // When
    Payment result = service.delete(paymentToDelete.getId());

    // Then
    assertThat(result, notNullValue());
    assertThat(result.getId(), is("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43"));
    assertThat(result.getType(), is(PaymentType.PAYMENT));
    assertThat(result.getVersion(), is(0));
    assertThat(result.getOrganisationId(), is("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb"));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testDeleteNonExistingPayment() throws PaymentNotFoundException {
    Payment paymentToDelete = Payment.builder()
      .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
      .withType(PaymentType.PAYMENT)
      .withVersion(0)
      .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
      .build();
    when(paymentDao.findById(paymentToDelete.getId())).thenReturn(Optional.empty());

    service.delete(paymentToDelete.getId());
  }
}

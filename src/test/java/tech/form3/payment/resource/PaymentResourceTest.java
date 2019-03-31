package tech.form3.payment.resource;

import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import tech.form3.payment.exception.InvalidInputException;
import tech.form3.payment.exception.PaymentAlreadyExistentException;
import tech.form3.payment.exception.PaymentNotFoundException;
import tech.form3.payment.model.Payment;
import tech.form3.payment.model.PaymentType;
import tech.form3.payment.service.PaymentService;

@RunWith (MockitoJUnitRunner.class)
public class PaymentResourceTest {

  @Mock
  private PaymentService paymentService;
  @InjectMocks
  private PaymentResource resource;

  @Test
  public void testGetAll() {
    when(paymentService.getAll()).thenReturn(asList(payment1, payment2));

    List<Payment> result = resource.getAll();

    assertThat(result, hasSize(2));
    assertThat(result, containsInAnyOrder(payment1, payment2));
  }

  @Test
  public void testGetById() throws PaymentNotFoundException {
    when(paymentService.getById(payment1.getId())).thenReturn(payment1);
    when(paymentService.getById(payment2.getId())).thenReturn(payment2);

    assertThat(resource.getById(payment1.getId()), is(payment1));
    assertThat(resource.getById(payment2.getId()), is(payment2));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testGetByIdForNonExistingPayment() throws PaymentNotFoundException {
    when(paymentService.getById(anyString())).thenThrow(new PaymentNotFoundException());

    resource.getById("NONEXISTING");
  }

  @Test
  public void testCreate() throws PaymentAlreadyExistentException {
    when(paymentService.create(payment1)).thenReturn(payment1);

    ResponseEntity<Payment> result = resource.create(payment1);

    assertThat(result.getBody(), is(payment1));
  }

  @Test (expected = PaymentAlreadyExistentException.class)
  public void testCreatePaymentAlreadyExistent() throws PaymentAlreadyExistentException {
    when(paymentService.create(any(Payment.class))).thenThrow(new PaymentAlreadyExistentException());

    resource.create(payment1);
  }

  @Test
  public void testUpdate() throws PaymentNotFoundException, InvalidInputException {
    when(paymentService.update(payment1.getId(), payment1)).thenReturn(payment1);

    ResponseEntity<Payment> result = resource.update(payment1.getId(), payment1);

    assertThat(result.getBody(), is(payment1));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testUpdateNonExistingPayment() throws PaymentNotFoundException, InvalidInputException {
    when(paymentService.update(anyString(), any(Payment.class))).thenThrow(new PaymentNotFoundException());

    resource.update("NONEXISTING", payment1);
  }

  @Test
  public void testDelete() throws PaymentNotFoundException {
    when(paymentService.delete(payment1.getId())).thenReturn(payment1);
    when(paymentService.delete(payment2.getId())).thenReturn(payment2);

    assertThat(resource.delete(payment1.getId()).getBody(), is(payment1));
    assertThat(resource.delete(payment2.getId()).getBody(), is(payment2));
  }

  @Test (expected = PaymentNotFoundException.class)
  public void testDeleteNonExistingPayment() throws PaymentNotFoundException {
    when(paymentService.delete(anyString())).thenThrow(new PaymentNotFoundException());

    resource.delete("NONEXISTING");
  }

  private Payment payment1 = Payment.builder()
    .withId("4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43")
    .withType(PaymentType.PAYMENT)
    .withVersion(0)
    .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
    .build();

  private Payment payment2 = Payment.builder()
    .withId("216d4da9-e59a-4cc6-8df3-3da6e7580b77")
    .withType(PaymentType.PAYMENT)
    .withVersion(0)
    .withOrganisationId("743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb")
    .build();
}

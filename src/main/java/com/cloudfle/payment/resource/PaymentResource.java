package com.cloudfle.payment.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudfle.payment.exception.PaymentAlreadyExistentException;
import com.cloudfle.payment.exception.PaymentNotFoundException;
import com.cloudfle.payment.model.Payment;
import com.cloudfle.payment.service.PaymentService;

@RestController
@RequestMapping (value = "/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentResource {

  @Autowired
  private PaymentService paymentService;

  /**
   * Returns the list of all the payments on the system
   *
   * @return a list of {@link Payment}
   */
  @RequestMapping (method = RequestMethod.GET)
  public List<Payment> getAll() {
    return paymentService.getAll();
  }

  /**
   * Returns a payment, if exists, given its id.
   *
   * @param paymentId the payment unique identifier
   * @return a {@link Payment} object
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  @RequestMapping (value = "{paymentId}", method = RequestMethod.GET)
  public Payment getById(
      @PathVariable final String paymentId)
      throws PaymentNotFoundException {
    return paymentService.getById(paymentId);
  }

  /**
   * Creates a new payment
   *
   * @param payment the {@link Payment} object to create
   * @return the newly created {@link Payment}
   * @throws PaymentAlreadyExistentException when a {@link Payment} with the same id already exists
   */
  @RequestMapping (method = RequestMethod.POST)
  public ResponseEntity<Payment> create(
      @RequestBody @Valid final Payment payment)
      throws PaymentAlreadyExistentException {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(paymentService.create(payment));
  }

  /**
   * Updates a payment, given its id
   *
   * @param paymentId the unique identifier for the payment being updated
   * @param payment the {@link Payment} object to update
   * @return the newly updated {@link Payment}
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  @RequestMapping (value = "{paymentId}", method = RequestMethod.PUT)
  public ResponseEntity<Payment> update(
      @PathVariable final String paymentId,
      @RequestBody @Validated final Payment payment)
      throws PaymentNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
      .body(paymentService.update(paymentId, payment));
  }

  /**
   * Deletes a payment, given its id
   *
   * @param paymentId the id of the payment being deleted
   * @return the deleted {@link Payment}
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  @RequestMapping (value = "{paymentId}", method = RequestMethod.DELETE)
  public ResponseEntity<Payment> delete(
      @PathVariable final String paymentId)
      throws PaymentNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
      .body(paymentService.delete(paymentId));
  }
}

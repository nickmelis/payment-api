package tech.form3.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.form3.payment.dao.PaymentDao;
import tech.form3.payment.exception.PaymentAlreadyExistentException;
import tech.form3.payment.exception.PaymentNotFoundException;
import tech.form3.payment.model.Payment;
import tech.form3.payment.model.mapper.PaymentMapper;

@Service
public class PaymentService {

  @Autowired
  private PaymentDao paymentDao;

  /**
   * Returns the list of all the payments on the system
   *
   * @return a list of {@link Payment}
   */
  public List<Payment> getAll() {
    return paymentDao.findAll().stream()
      .map(PaymentMapper::map)
      .collect(Collectors.toList());
  }

  /**
   * Returns a payment, if exists, given its id.
   *
   * @param id the payment unique identifier
   * @return a {@link Payment} object
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  public Payment getById(String id) throws PaymentNotFoundException {
    Optional<tech.form3.payment.domain.Payment> payment = paymentDao.findById(id);
    if (!payment.isPresent()) {
      throw new PaymentNotFoundException(String.format("Payment with id %s not found", id));
    }
    return PaymentMapper.map(payment.get());
  }

  /**
   * Creates a new payment
   *
   * @param newPayment the {@link Payment} object to create
   * @return the newly created {@link Payment}
   * @throws PaymentAlreadyExistentException when a {@link Payment} with the same id already exists
   */
  public Payment create(Payment newPayment) throws PaymentAlreadyExistentException {
    Optional<tech.form3.payment.domain.Payment> payment = paymentDao.findById(newPayment.getId());
    if (payment.isPresent()) {
      throw new PaymentAlreadyExistentException(String.format("Payment with id %s already exists", newPayment.getId()));
    }
    return PaymentMapper.map(paymentDao.save(PaymentMapper.map(newPayment)));
  }

  /**
   * Updates a payment, given its id
   *
   * @param id the unique identifier for the payment being updated
   * @param newPayment the {@link Payment} object to update
   * @return the newly updated {@link Payment}
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  public Payment update(String id, Payment newPayment) throws PaymentNotFoundException {
    Optional<tech.form3.payment.domain.Payment> payment = paymentDao.findById(newPayment.getId());
    if (!payment.isPresent()) {
      throw new PaymentNotFoundException(String.format("Payment with id %s not found", id));
    }
    return PaymentMapper.map(paymentDao.save(PaymentMapper.map(newPayment)));
  }

  /**
   * Deletes a payment, given its id
   *
   * @param id the id of the payment being deleted
   * @return the deleted {@link Payment}
   * @throws PaymentNotFoundException when no payment was found for the given id
   */
  public Payment delete(String id) throws PaymentNotFoundException {
    Optional<tech.form3.payment.domain.Payment> payment = paymentDao.findById(id);
    if (!payment.isPresent()) {
      throw new PaymentNotFoundException(String.format("Payment with id %s not found", id));
    }
    paymentDao.delete(payment.get());
    return PaymentMapper.map(payment.get());
  }
}

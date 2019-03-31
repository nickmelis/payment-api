package tech.form3.payment.dao;

import java.util.List;
import java.util.Optional;

import tech.form3.payment.domain.Payment;

/**
 * Interface to the repository
 */
public interface PaymentDao {

  /**
   * Retrieves the list of all the payments in the repository
   *
   * @return a list of {@link Payment}s
   */
  List<Payment> findAll();

  /**
   * Retrieves a payment from the repository, given its id
   *
   * @param id the payment unique identifier
   * @return an {@link Optional} class with a {@link Payment},
   *         or an empty one if no payment with the given id exists.
   */
  Optional<Payment> findById(String id);

  /**
   * Inserts or updates a payment in the repository
   *
   * @param payment the {@link Payment} to save
   * @return the newly saved {@link Payment}
   */
  Payment save(Payment payment);

  /**
   * Deletes a {@link Payment} from the repository
   *
   * @param payment the {@link Payment} to delete
   */
  void delete(Payment payment);
}

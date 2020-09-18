package com.cloudfle.payment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.cloudfle.payment.domain.Payment;

/**
 * Stub implementation of the Payment repository interface
 */
@Repository
@Profile ("dev")
public class StubPaymentDao implements PaymentDao {

  private final Map<String, Payment> payments = new HashMap<>();

  /*
   * (non-Javadoc)
   * @see com.cloudfle.payment.dao.PaymentDao#findAll()
   */
  @Override
  public List<Payment> findAll() {
    return new ArrayList<>(payments.values());
  }

  /*
   * (non-Javadoc)
   * @see com.cloudfle.payment.dao.PaymentDao#findById(java.lang.String)
   */
  @Override
  public Optional<Payment> findById(String id) {
    if (!payments.containsKey(id)) {
      return Optional.empty();
    }
    return Optional.of(payments.get(id));
  }

  /*
   * (non-Javadoc)
   * @see com.cloudfle.payment.dao.PaymentDao#save(com.cloudfle.payment.domain.Payment)
   */
  @Override
  public Payment save(Payment payment) {
    payments.put(payment.getId(), payment);
    return payment;
  }

  /*
   * (non-Javadoc)
   * @see com.cloudfle.payment.dao.PaymentDao#delete(com.cloudfle.payment.domain.Payment)
   */
  @Override
  public void delete(Payment payment) {
    payments.remove(payment.getId());
  }
}

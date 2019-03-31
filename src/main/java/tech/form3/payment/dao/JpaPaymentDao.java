package tech.form3.payment.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.form3.payment.domain.Payment;

/**
 * JPA based implementation of the Payment repository interface
 */
@Repository
@Profile ("!dev")
public interface JpaPaymentDao extends JpaRepository<Payment, String>, PaymentDao {

}

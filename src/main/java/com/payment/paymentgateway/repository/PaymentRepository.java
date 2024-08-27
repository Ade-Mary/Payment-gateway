package com.payment.paymentgateway.repository;

import com.payment.paymentgateway.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByTransactionStatus(String transactionStatus);

    Optional<Payment> findByReference(String reference);
}

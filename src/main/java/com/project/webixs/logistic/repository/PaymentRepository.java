package com.project.webixs.logistic.repository;

import com.project.webixs.logistic.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

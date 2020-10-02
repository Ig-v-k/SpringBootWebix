package com.project.webixs.logistic.controller;

import com.project.webixs.logistic.common.ReadOnlyController;
import com.project.webixs.logistic.model.Payment;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
@Scope("request")
public class PaymentController extends ReadOnlyController<Payment, Integer> {
  public PaymentController(JpaRepository<Payment, Integer> repo) {
	super(repo);
  }
}
package com.project.webixs.logistic.controller;

import com.project.webixs.logistic.common.ReadOnlyController;
import com.project.webixs.logistic.model.Status;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
@Scope("request")
public class StatusController extends ReadOnlyController<Status, Integer> {
  public StatusController(JpaRepository<Status, Integer> repo) {
	super(repo);
  }
}
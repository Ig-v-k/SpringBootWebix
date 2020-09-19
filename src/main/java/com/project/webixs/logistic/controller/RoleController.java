package com.project.webixs.logistic.controller;

import com.project.webixs.logistic.common.ReadOnlyController;
import com.project.webixs.logistic.model.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
@Scope("request")
public class RoleController extends ReadOnlyController<Role, Integer> {
  public RoleController(JpaRepository<Role, Integer> repo) {
	super(repo);
  }
}
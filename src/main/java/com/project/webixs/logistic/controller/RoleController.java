package com.project.webixs.logistic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController extends ReadOnlyController<Role, Integer> {

  public RoleController(JpaRepository<Role,Integer> repo) {
	super(repo);
  }

}
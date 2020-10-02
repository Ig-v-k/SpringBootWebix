package com.project.webixs.logistic.controller;

import com.project.webixs.logistic.common.ReadOnlyController;
import com.project.webixs.logistic.common.exception.ForbiddenException;
import com.project.webixs.logistic.model.Logger;
import com.project.webixs.logistic.model.User;
import com.project.webixs.logistic.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("request")
@RequestMapping("api/logger")
public class LoggerController extends ReadOnlyController<Logger, Integer> {

  private final LoggerRepository repository;

  @Value(value = "${role.system_admin}")
  private Integer roleSystemAdmin;

  public LoggerController(LoggerRepository repo) {
	super(repo);
	this.repository = repo;
  }

  @Override
  public List getAll() throws ForbiddenException {
	User loggedUser = userBean.getUser();
	if (loggedUser.getRoleId().equals(roleSystemAdmin))
	  return repository.getExtendedAll();
	else return repository.getExtendedByCompany(loggedUser.getCompanyId());
  }
}
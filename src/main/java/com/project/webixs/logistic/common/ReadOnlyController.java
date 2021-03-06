package com.project.webixs.logistic.common;

import com.project.webixs.logistic.common.exception.ForbiddenException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public class ReadOnlyController<T, ID extends Serializable> extends CommonController {

  private final JpaRepository<T, ID> repo;

  public ReadOnlyController(JpaRepository<T, ID> repo) {
	this.repo = repo;
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET)
  public List<T> getAll() throws ForbiddenException {
	return repo.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public T findById(@PathVariable("id") ID id) {
	return repo.findById(id).orElse(null);
  }
}
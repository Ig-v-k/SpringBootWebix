package com.project.webixs.logistic.controller.abstractController;

import com.project.webixs.logistic.controller.abstractLogger.AbstractLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class AbstractController<T, ID extends Serializable> extends AbstractLogger<T> {

  protected JpaRepository<T, ID> repo;
  @PersistenceContext
  private EntityManager entityManager;

  @Value("${badRequest.insert}")
  private String badRequestInsert;

  @Value("${badRequest.update}")
  private String badRequestUpdate;

  @Value("${badRequest.delete}")
  private String badRequestDelete;



  public AbstractController(JpaRepository<T, ID> repo) {
	this.repo = repo;
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET)
  public List<T> getAll()throws BadRequestException, ForbiddenException {
	return repo.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public
  T findById(@PathVariable("id") ID id) throws BadRequestException,ForbiddenException {
	return repo.findById(id).orElse(null);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public T insert(@RequestBody T object) throws BadRequestException,ForbiddenException {
	T ret = null;
	if ((ret = repo.saveAndFlush(object)) != null) {
	  entityManager.refresh(ret);
	  logCreateAction(object);
	  return ret;
	}
	throw new BadRequestException(badRequestInsert);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public String update(@PathVariable ID id, @RequestBody T object) throws BadRequestException,ForbiddenException {
	T oldObject = cloner.deepClone(repo.findById(id).orElse(null));
	if (repo.saveAndFlush(object) != null) {
	  logUpdateAction(object, oldObject);
	  return "Success";
	}
	throw new BadRequestException(badRequestUpdate);
  }

  @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
  public String delete(@PathVariable ID id) throws BadRequestException,ForbiddenException {
	try {
	  T object = repo.findById(id).orElse(null);
	  repo.deleteById(id);
	  logDeleteAction(object);
	  return "Success";
	} catch (Exception ex) {
	  ex.printStackTrace();
	  throw new BadRequestException(badRequestDelete);
	}
  }

}
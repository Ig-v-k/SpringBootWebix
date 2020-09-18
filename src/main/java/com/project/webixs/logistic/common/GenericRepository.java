package com.project.webixs.logistic.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericRepository {
  @PersistenceContext
  protected EntityManager entityManager;
}
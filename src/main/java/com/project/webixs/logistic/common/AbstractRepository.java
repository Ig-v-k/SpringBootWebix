package com.project.webixs.logistic.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository {
  @PersistenceContext
  protected EntityManager entityManager;
}
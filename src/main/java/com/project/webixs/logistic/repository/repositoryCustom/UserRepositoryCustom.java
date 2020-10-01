package com.project.webixs.logistic.repository.repositoryCustom;

import com.project.webixs.logistic.model.User;

public interface UserRepositoryCustom {
  User login(String username, String password);
  User getOneById(Integer id);
}
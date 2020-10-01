package com.project.webixs.logistic.repository;

import com.project.webixs.logistic.model.User;
import com.project.webixs.logistic.repository.repositoryCustom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
  List<User> getAllByRoleId(Integer roleId);
}
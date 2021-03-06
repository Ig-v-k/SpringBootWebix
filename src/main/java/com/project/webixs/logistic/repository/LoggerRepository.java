package com.project.webixs.logistic.repository;

import com.project.webixs.logistic.model.Logger;
import com.project.webixs.logistic.repository.repositoryCustom.LoggerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean
public interface LoggerRepository extends JpaRepository<Logger,Integer>, LoggerRepositoryCustom {
}
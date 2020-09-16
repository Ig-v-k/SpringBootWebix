package com.project.webixs.logistic.repository.repositoryCustom;

import com.project.webixs.logistic.model.modelCustom.LoggerCompanyUserRole;

import java.util.List;

public interface LoggerRepositoryCustom {
  List<LoggerCompanyUserRole> getExtendedAll();
  List<LoggerCompanyUserRole> getExtendedByCompany(Integer companyId);
}
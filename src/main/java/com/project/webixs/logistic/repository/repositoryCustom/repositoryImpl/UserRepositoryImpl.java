package com.project.webixs.logistic.repository.repositoryCustom.repositoryImpl;

import com.project.webixs.logistic.common.AbstractRepository;
import com.project.webixs.logistic.model.User;
import com.project.webixs.logistic.repository.repositoryCustom.UserRepositoryCustom;

public class UserRepositoryImpl extends AbstractRepository implements UserRepositoryCustom {

  private static final String SQL_LOGIN_NO_COMPANY = "select id, username, first_name, last_name, registration_date," +
		"email, role_id, status_id, company_id, notification_type_id, location_id from user where username=? and password=SHA2(?,512)" +
		" and company_id is null;";
  private static final String SQL_LOGIN="select u.id, username, first_name, last_name, registration_date," +
		"email, role_id, status_id, company_id, notification_type_id, location_id from user u inner join company c on u.company_id=c.id where username=? and password=SHA2(?,512) and c.name=?;";

  @Override
  public User login(String username, String password, String companyName) {
	if ("".equals(companyName))
	  return (User) entityManager.createNativeQuery(SQL_LOGIN_NO_COMPANY,"UserMapping").
			setParameter(1,username).setParameter(2,password).getResultList().stream().findFirst().orElse(null);
	return (User) entityManager.createNativeQuery(SQL_LOGIN,"UserMapping").
		  setParameter(1,username).setParameter(2,password).setParameter(3,companyName).getResultList().stream().findFirst().orElse(null);
  }
}
package com.project.webixs.logistic.repository.repositoryCustom.repositoryImpl;

import com.project.webixs.logistic.common.AbstractRepository;
import com.project.webixs.logistic.model.User;
import com.project.webixs.logistic.repository.repositoryCustom.UserRepositoryCustom;

public class UserRepositoryImpl extends AbstractRepository implements UserRepositoryCustom {

  private static final String SQL_LOGIN = "select u.id, username, password, first_name, last_name, registration_date," +
		"email, role_id, status_id, company_id, notification_type_id, location_id, usr_payments from usr u where username = ? and password = crypt(?, password)";

  private static final String SQL_GET_ONE = "select * from usr where id = ?";

  @Override
  public User login(String username, String password) {
	return (User) entityManager
		  .createNativeQuery(SQL_LOGIN, "UserMapping")
		  .setParameter(1, username)
		  .setParameter(2, password)
		  .getResultList()
		  .stream()
		  .findFirst()
		  .orElse(null);
  }

  @Override
  public User getOneById(Integer id) {
	return (User) entityManager
		  .createNativeQuery(SQL_GET_ONE)
		  .setParameter(1, id)
		  .getResultList()
		  .stream()
		  .findFirst()
		  .orElse(null);
  }
}

package com.project.webixs.logistic.controller;

import com.project.webixs.logistic.common.exception.ForbiddenException;
import com.project.webixs.logistic.controller.abstractController.AbstractController;
import com.project.webixs.logistic.model.LoginInfo;
import com.project.webixs.logistic.model.User;
import com.project.webixs.logistic.repository.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log
@CommonsLog
@RestController
@RequestMapping("api/user")
@Scope("request")
public class UserController extends AbstractController<User, Integer> {

  private final UserRepository repository;

  @Value(value = "${role.system_admin}")
  private Integer roleSystemAdmin;

  public UserController(UserRepository repo) {
	super(repo);
	repository = repo;
  }

  @Override
  public List<User> getAll() throws ForbiddenException {
    int roleId = userBean.getUser().getRoleId();
	if ((roleId == 1) || (roleId == 2))
	  return repository.findAll();
	throw new ForbiddenException("Forbidden");
  }

  @RequestMapping(value = {"/state"})
  public User checkState() throws ForbiddenException {
	if (userBean.getLoggedIn())
	  return userBean.getUser();
	throw new ForbiddenException("Forbidden");
  }

  @RequestMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if (session != null)
	  session.invalidate();
	return "Success";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public User login(@RequestBody LoginInfo userInformation) throws ForbiddenException {
	User user = repository.login(userInformation.getUsername(), userInformation.getPassword());
	if (null != user) {
	  userBean.setLoggedIn(true);
	  userBean.setUser(user);
	  return user;
	}
	throw new ForbiddenException("Forbidden");
  }

}
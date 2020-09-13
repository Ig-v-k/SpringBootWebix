package com.project.webixs.logistic.session;

import com.project.webixs.logistic.model.User;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("session")
@Data
public class UserBean {

  private User user;
  private Boolean loggedIn;

  @PostConstruct
  void init(){
	user=new User();
	loggedIn=false;
  }
}
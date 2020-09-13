package com.project.webixs.logistic.common;

import com.project.webixs.logistic.common.exception.BadRequestException;
import com.project.webixs.logistic.common.exception.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommonController {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleException(BadRequestException e) {
	return e.getMessage();
  }

  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public String handleException(ForbiddenException e) {
	return e.getMessage();
  }
}
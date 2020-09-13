package com.project.webixs.logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
public class LogisticApplication {
  public static void main(String[] args) {
	SpringApplication.run(LogisticApplication.class, args);
  }
}
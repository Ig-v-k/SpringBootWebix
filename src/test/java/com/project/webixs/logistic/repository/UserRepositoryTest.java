package com.project.webixs.logistic.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@RunWith(SpringRunner.class)
class UserRepositoryTest {

  @Autowired
  UserRepository repository;

  @Test
  @Transactional
  @DisplayName("Test1")
  public void getAUserFromDatabaseById() {
    assertNull(repository.getOneById(1));
  }

  @Test
  @Transactional
  @DisplayName("Test2")
  public void getAUserFromDatabaseByIdAndShowHim() {
    System.out.println("\nTest2: -----> " + repository.getOneById(0) + "\n");
  }

  @Test
  @Transactional
  @DisplayName("Test3")
  public void getAUserFromDatabaseByIdAndShowHimFromLogin() {
    System.out.println("\nTest3: -----> " + repository.login("admin", "admin") + "\n");
  }
}
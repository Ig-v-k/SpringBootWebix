package com.project.webixs.logistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Objects;

@SpringBootApplication
public class LogisticApplication {
  public static void main(String[] args) {
	SpringApplication.run(LogisticApplication.class, args);
  }
}

/*
 *   Controllers
 */
class AbstractRestController<T, R extends JpaRepository<T, ?>> {
  protected R repository;

  public AbstractRestController(R repository) {
	this.repository = repository;
  }

  @GetMapping
  public Page<T> list(@PageableDefault Pageable pageable) {
	return repository.findAll(pageable);
  }

  @GetMapping("{id}")
  public T getOne(@PathVariable("id") T obj) {
	return obj;
  }

  @PostMapping
  public T add(@RequestBody T obj) {
	return repository.save(obj);
  }

  @PutMapping("{id}")
  public T update(@PathVariable("id") T dbObj, @RequestBody T obj) {
	BeanUtils.copyProperties(obj, dbObj, "id");

	return repository.save(dbObj);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") T dbObj) {
	repository.delete(dbObj);
  }
}

@RestController
@RequestMapping("/api/mark")
class MarkRestController extends AbstractRestController<Mark, MarkRepository> {
  public MarkRestController(MarkRepository repository) {
	super(repository);
  }
}

/*
 *   Repositories
 */
interface MarkRepository extends JpaRepository<Mark, Long> {
}

/*
 * 	Entities
 */
@Data
@Entity
class Mark {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  private String name;
}

@Data
@Entity
@ToString
//@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Basic
  @Column(name = "username", nullable = true, length = 64)
  private String username;

  @Basic
  @Column(name = "password", nullable = true, length = 128)
  @JsonIgnore
  private String password;

  @Basic
  @Column(name = "first_name", nullable = true, length = 64)
  private String firstName;

  @Basic
  @Column(name = "last_name", nullable = true, length = 64)
  private String lastName;

  @Basic
  @Column(name = "email", nullable = false, length = 64)
  private String email;

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	User user = (User) o;
	return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
  }
}

@Data
@Component
@Scope("session")
class UserBean {

  private User user;
  private Boolean loggedIn;

  @PostConstruct
  void init() {
	user = new User();
	loggedIn = false;
  }
}
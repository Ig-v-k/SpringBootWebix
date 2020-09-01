package com.project.webixs.logistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class LogisticApplication {
  public static void main(String[] args) {
	SpringApplication.run(LogisticApplication.class, args);
  }
}

/*
 *   qualifier-Controllers
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

@RestController
@RequestMapping("/api/user")
@Scope("request")
class UserRestController extends AbstractRestController<User, UserRepository> {
  public UserRestController(UserRepository repository) {
	super(repository);
  }

  @RequestMapping(value = {"/state"})
  public User checkState() throws ForbiddenException {
	if (userBean.getLoggedIn())
	  return userBean.getUser();
	throw new ForbiddenException("Forbidden");
  }
}

class CommonController {

  @Autowired
  protected UserBean userBean;


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

/*
 * 	qualifier-Repositories
 */
interface MarkRepository extends JpaRepository<Mark, Long> {
}
interface UserRepository extends JpaRepository<User, Integer> {
}

/*
 * 	qualifier-Utils
 */
@WebFilter("/api/*")
class AccessFilter implements Filter {

  @org.springframework.beans.factory.annotation.Value("${'${path.public}'.split(', ')}")
  private List<String> publicPaths;

  private WebApplicationContext springContext;

  @Override
  public void init(FilterConfig filterConfig) {
	springContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	UserBean userBean = (UserBean) springContext.getBean("userBean");
	if (
		  !request.getRequestURI().startsWith("/api/user/register")
		  && !request.getRequestURI().startsWith("/api/user/check/")
		  && !userBean.getLoggedIn()
		  && !publicPaths.contains(request.getRequestURI())
	) {
	  response.sendError(401);
	} else {
	  filterChain.doFilter(servletRequest, servletResponse);
	}
	// filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}

/*
 *	qualifier-Entities
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

/*
* 	Exceptions
*/
class BadRequestException extends Exception {
  private static final long serialVersionUID = -1300922631131923484L;

  public BadRequestException(String message) {
	super(message);
  }
}


class ForbiddenException extends Exception {
  private static final long serialVersionUID = -1300922631131923484L;

  public ForbiddenException(String message) {
	super(message);
  }
}
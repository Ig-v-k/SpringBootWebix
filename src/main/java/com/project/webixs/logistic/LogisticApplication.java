package com.project.webixs.logistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
@EnableAsync
public class LogisticApplication {
  public static void main(String[] args) {
	SpringApplication.run(LogisticApplication.class, args);
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
  @Column(name = "registration_date", nullable = false)
  private Timestamp registrationDate;

  @Basic
  @Column(name = "token", nullable = true, length = 64)
  @JsonIgnore
  private String token;

  @Basic
  @Column(name = "email", nullable = false, length = 64)
  private String email;

  @Basic
  @Column(name = "role_id", nullable = false)
  private Integer roleId;

  @Basic
  @Column(name = "status_id", nullable = false)
  private Integer statusId;

  @Basic
  @Column(name = "company_id", nullable = true)
  private Integer companyId;

  @Basic
  @Column(name = "notification_type_id", nullable = true)
  private Integer notificationTypeId;

  @Basic
  @Column(name = "location_id", nullable = true)
  private Integer locationId;

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
@Entity
class Status {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "value", nullable = false, length = 64)
  private String value;

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	Status status = (Status) o;
	return Objects.equals(id, status.id);
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
  }
}

@Data
@Entity
class Role {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "value", nullable = false, length = 64)
  private String value;

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	Role role = (Role) o;
	return Objects.equals(id, role.id);
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
  }
}

@Data
class LoginInfo {
  private String username;
  private String password;
  private String companyName;
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

@Log
@RestController
@RequestMapping("/api/user")
@Scope("request")
class UserRestController extends AbstractRestController<User, UserRepository> {

  final UserBean userBean;

  public UserRestController(UserRepository repository, UserBean userBean) {
	super(repository);
	this.userBean = userBean;
  }

  @RequestMapping(value = {"/state"})
  public User checkState() throws ForbiddenException {
	userBean.setLoggedIn(true);
	if (userBean.getLoggedIn()) {
	  return userBean.getUser();
	}
	throw new ForbiddenException("Forbidden");
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public User login(@RequestBody LoginInfo userInformation) throws ForbiddenException {
	User user = repository.findByUsernameAndPassword(userInformation.getUsername(), userInformation.getPassword());
	if (user != null) {
	  userBean.setLoggedIn(true);
	  userBean.setUser(user);
	  return user;
	}
	throw new ForbiddenException("Forbidden");
  }
}

@RestController
@RequestMapping("api/status")
@Scope("request")
class StatusController extends ReadOnlyController<Status, Integer> {
  public StatusController(JpaRepository<Status, Integer> repo) {
	super(repo);
  }
}

@RestController
@RequestMapping("api/role")
@Scope("request")
class RoleController extends ReadOnlyController<Role, Integer> {
  public RoleController(JpaRepository<Role, Integer> repo) {
	super(repo);
  }
}

class ReadOnlyController<T, ID extends Serializable> extends CommonController {

  private final JpaRepository<T, ID> repo;

  public ReadOnlyController(JpaRepository<T, ID> repo) {
	this.repo = repo;
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET)
  public List<T> getAll() throws ForbiddenException {
	return repo.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public T findById(@PathVariable("id") ID id) throws ForbiddenException {
	return repo.findById(id).orElse(null);
  }
}

class CommonController {

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
interface UserRepositoryCustom {
  User login(String username, String password, String companyName);
//  List<UserLocation> getAllExtendedByCompanyIdAndStatusIdNot(Integer companyId, Integer statusId);
}

interface MarkRepository extends JpaRepository<Mark, Long> {
}

interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
  User findByUsernameAndPassword(String username, String password);
}

class UserRepositoryImpl extends CustomRepositoryImpl implements UserRepositoryCustom {
  private static final String SQL_LOGIN_NO_COMPANY =
		"SELECT id, username, first_name, last_name, registration_date, email, role_id, status_id, company_id, notification_type_id, location_id" +
			  " FROM user WHERE username=?" +
			  " AND password=SHA2(?,512)" +
			  " AND company_id IS NULL;";
  private static final String SQL_LOGIN =
		"select u.id, username, first_name, last_name, registration_date, email, role_id, status_id, company_id, notification_type_id, location_id" +
			  " FROM user u INNER JOIN company c on u.company_id=c.id" +
			  " WHERE username=? AND password=SHA2(?,512) AND c.name=?;";

  @Override
  public User login(String username, String password, String companyName) {
	if ("".equals(companyName))
	  return (User) entityManager.createNativeQuery(SQL_LOGIN_NO_COMPANY, "UserMapping").
			setParameter(1, username).setParameter(2, password).getResultList().stream().findFirst().orElse(null);
	return (User) entityManager.createNativeQuery(SQL_LOGIN, "UserMapping").
		  setParameter(1, username).setParameter(2, password).setParameter(3, companyName).getResultList().stream().findFirst().orElse(null);
  }
}

class CustomRepositoryImpl {
  @PersistenceContext
  protected EntityManager entityManager;
}

/*
 * 	qualifier-Utils
 */
@WebFilter("/api/*")
class AccessFilter implements Filter {

  @Value("#{'${path.public}'.split(',')}")
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
 * 	qualifier-Exceptions
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

/*
 * 	qualifier-Components
 */
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
 * 	qualifier-Other
 */
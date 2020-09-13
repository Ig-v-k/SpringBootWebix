package com.project.webixs.logistic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rits.cloning.Cloner;
import com.sun.jmx.snmp.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
@SqlResultSetMapping(
	  name = "LoggerMapping",
	  classes = @ConstructorResult(
			targetClass = LoggerCompanyUserRole.class,
			columns = {
				  @ColumnResult(name = "id", type = Integer.class),
				  @ColumnResult(name = "action_type", type = String.class),
				  @ColumnResult(name = "action_details", type = String.class),
				  @ColumnResult(name = "table_name", type = String.class),
				  @ColumnResult(name = "created", type = Date.class),
				  @ColumnResult(name = "atomic", type = Byte.class),
				  @ColumnResult(name = "user_id", type = Integer.class),
				  @ColumnResult(name = "company_id", type = Integer.class),
				  @ColumnResult(name = "company_name", type = String.class),
				  @ColumnResult(name = "username", type = String.class),
				  @ColumnResult(name = "role", type = String.class)

			}
	  )
)
@MappedSuperclass
@Data
@NoArgsConstructor
class LoggerCompanyUserRole extends Logger implements Serializable {

  private String companyName;
  private String username;
  private String role;

  public LoggerCompanyUserRole(Integer id, String actionType, String actionDetails, String tableName, Date created, Byte atomic, Integer userId, Integer companyId, String companyName, String username, String role) {
	super(id, actionType, actionDetails, tableName, (java.sql.Timestamp) created, atomic, userId, companyId);
	this.companyName = companyName;
	this.username = username;
	this.role = role;
  }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class Logger {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "action_type", nullable = false, length = 128)
  private String actionType;

  @Basic
  @Column(name = "action_details", nullable = false, length = 1024)
  private String actionDetails;

  @Basic
  @Column(name = "table_name", nullable = false, length = 128)
  private String tableName;

  @Basic
  @Column(name = "created", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy. HH:mm", timezone = "Europe/Belgrade")
  private java.sql.Timestamp created;

  @Basic
  @Column(name = "atomic", nullable = false)
  private Byte atomic;

  @Basic
  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Basic
  @Column(name = "company_id", nullable = true)
  private Integer companyId;

  public Logger(Integer userId, String actionType, String actionDetails, String tableName, Byte atomic, Integer companyId) {
	this.userId = userId;
	this.actionType = actionType;
	this.actionDetails = actionDetails;
	this.tableName = tableName;
	this.atomic = atomic;
	this.companyId = companyId;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	Logger logger = (Logger) o;
	return java.util.Objects.equals(id, logger.id);
  }

  @Override
  public int hashCode() {
	return java.util.Objects.hash(id);
  }

  public enum ActionType {
	CREATE("create"),
	UPDATE("update"),
	READ("read"),
	DELETE("delete");

	private final String text;

	ActionType(final String text) {
	  this.text = text;
	}

	@Override
	public String toString() {
	  return text;
	}
  }
}

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Basic
  @Column(name = "username", length = 64)
  private String username;

  @Basic
  @Column(name = "password", length = 128)
  @JsonIgnore
  private String password;

  @Basic
  @Column(name = "first_name", length = 64)
  private String firstName;

  @Basic
  @Column(name = "last_name", length = 64)
  private String lastName;

  @Basic
  @Column(name = "registration_date", nullable = false)
  private Timestamp registrationDate;

  @Basic
  @Column(name = "token", length = 64)
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
  @Column(name = "company_id")
  private Integer companyId;

  @Basic
  @Column(name = "notification_type_id")
  private Integer notificationTypeId;

  @Basic
  @Column(name = "location_id")
  private Integer locationId;

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	User user = (User) o;
	return java.util.Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
	return java.util.Objects.hash(id);
  }
}

@Data
@Entity
@Table(name = "status")
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
	return java.util.Objects.equals(id, status.id);
  }

  @Override
  public int hashCode() {
	return java.util.Objects.hash(id);
  }
}

@Data
@Entity
@Table(name = "role")
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
	return java.util.Objects.hash(id);
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

  private final UserRepository userRepository;
  final UserBean userBean;

  public UserRestController(UserRepository repository, UserBean userBean) {
	super(repository);
	this.userRepository = repository;
	this.userBean = userBean;
  }

  @RequestMapping(value = {"/state"})
  public User checkState() throws ForbiddenException {
//	userBean.setLoggedIn(true);
	if (userBean.getLoggedIn()) {
	  return userBean.getUser();
	}
	throw new ForbiddenException("Forbidden");
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public User login(@RequestBody LoginInfo userInformation) throws ForbiddenException {
	User user = Optional
		  .ofNullable(
				userRepository.findByUsernameAndPassword(
					  userInformation.getUsername(),
					  userInformation.getPassword()))
		  .orElseGet(
				() -> userRepository.findByUsername(
					  userInformation.getUsername()));
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

class GenericController<T, ID extends Serializable> extends GenericLogger<T> {

  private JpaRepository<T, ID> repo;
  @PersistenceContext
  private EntityManager entityManager;

  @Value("${badRequest.insert}")
  private String badRequestInsert;

  @Value("${badRequest.update}")
  private String badRequestUpdate;

  @Value("${badRequest.delete}")
  private String badRequestDelete;


  public GenericController(JpaRepository<T, ID> repo) {
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

  @Transactional
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public T insert(@RequestBody T object) throws BadRequestException, ForbiddenException {
	T ret = null;
	if ((ret = repo.saveAndFlush(object)) != null) {
	  entityManager.refresh(ret);
	  logCreateAction(object);
	  return ret;
	}
	throw new BadRequestException(badRequestInsert);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public String update(@PathVariable ID id, @RequestBody T object) throws BadRequestException, ForbiddenException {
	T oldObject = cloner.deepClone(repo.findById(id).orElse(null));
	if (repo.saveAndFlush(object) != null) {
	  logUpdateAction(object, oldObject);
	  return "Success";
	}
	throw new BadRequestException(badRequestUpdate);
  }

  @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
  public String delete(@PathVariable ID id) throws BadRequestException, ForbiddenException {
	try {
	  T object = repo.findById(id).orElse(null);
	  repo.deleteById(id);
	  logDeleteAction(object);
	  return "Success";
	} catch (Exception ex) {
	  ex.printStackTrace();
	  throw new BadRequestException(badRequestDelete);
	}
  }
}

class GenericLogger<T> extends CommonController {

  protected Cloner cloner;
  private Class<T> type;
  private HttpSession httpSession;
  @Autowired
  private LoggerRepository loggerRepository;
  @Value("${loggerConfig.createMessage}")
  private String createMessage;
  @Value("${loggerConfig.updateMessage}")
  private String updateMessage;
  @Value("${loggerConfig.deleteMessage}")
  private String deleteMessage;

  public GenericLogger() {
	cloner = new Cloner();
	this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericLogger.class);
	this.httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
  }

  public void logCreateAction(T object) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.CREATE.toString(), createMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logUpdateAction(T newObject, T oldObject) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.UPDATE.toString(), updateMessage.replace("{oldEntity}", oldObject.toString()).replace("{newEntity}", newObject.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logDeleteAction(T object) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.DELETE.toString(), deleteMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logSpecificAction(String actionType, String actionDetails, String tableName) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), actionType, actionDetails, tableName, (byte) 0, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

}

abstract class GenericDeletableController<T extends Deletable, ID extends Serializable> extends GenericController<T, ID> {

  private DeletableRepository<T> repo;
  @Value("${badRequest.update}")
  private String badRequestUpdate;

  @Value("${badRequest.delete}")
  private String badRequestDelete;


  public GenericDeletableController(JpaRepository<T, ID> repo) {
	super(repo);
	if (repo instanceof DeletableRepository)
	  this.repo = (DeletableRepository) repo;
	else throw new RuntimeException("Repository must implement " + DeletableRepository.class.getSimpleName());
  }

  @Override
  public List<T> getAll() throws ForbiddenException {
	return repo.getAllByDeletedIs((byte) 0);
  }

  @Override
  public T findById(@PathVariable ID id) throws ForbiddenException {
	T object = super.findById(id);
	if (object == null || object.getDeleted().equals((byte) 1))
	  object = null;
	return object;
  }

  @Override
  public String update(@PathVariable ID id, @RequestBody T object) throws BadRequestException, ForbiddenException {
	if (findById(id) == null)
	  throw new BadRequestException(badRequestUpdate);
	return super.update(id, object);
  }


  @Override
  public String delete(@PathVariable ID id) throws BadRequestException, ForbiddenException {
	T object = findById(id);
	if (object == null)
	  throw new BadRequestException(badRequestDelete);
	object.setDeleted((byte) 1);
	logDeleteAction(object);
	return "Success";
  }
}

/*
 * 	qualifier-Repositories
 */
interface StatusRepository extends JpaRepository<Status, Integer> {
}


interface LoggerRepository extends JpaRepository<Logger, Integer>, LoggerRepositoryCustom {
}

interface UserRepositoryCustom {
  User login(String username, String password, String companyName);
//  List<UserLocation> getAllExtendedByCompanyIdAndStatusIdNot(Integer companyId, Integer statusId);
}

interface MarkRepository extends JpaRepository<Mark, Long> {
}

interface UserRepository extends JpaRepository<User, Integer> {
  User findByUsernameAndPassword(final String username, final String password);

  User findByUsername(final String username);
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

interface ExpenseRepositoryCustom {
  BigDecimal sumValueByVehicleIdAndCompanyIdAndDeletedAndExpenseTypeAndDateBetween(Integer vehicleId, Integer companyId, Byte deleted, Integer expenseTypeId, java.sql.Timestamp startDate, java.sql.Timestamp endDate);

  BigDecimal sumValueByCompanyIdAndDeletedAndExpenseTypeAndDateBetween(Integer companyId, Byte deleted, Integer expenseTypeId, java.sql.Timestamp startDate, java.sql.Timestamp endDate);
}


class CustomRepositoryImpl {
  @PersistenceContext
  protected EntityManager entityManager;
}

interface Deletable {
  Byte getDeleted();

  void setDeleted(Byte deleted);
}

interface DeletableRepository<T extends Deletable> {
  List<T> getAllByDeletedIs(Byte deleted);
}

interface HasCompanyId {
  Integer getCompanyId();
}

interface HasCompanyIdAndDeletableRepository<T extends HasCompanyId & Deletable> extends DeletableRepository<T> {
  List<T> getAllByCompanyIdAndDeletedIs(Integer companyId, Byte deleted);
}

interface HasCompanyIdRepository<T extends HasCompanyId> {
  List<T> getAllByCompanyId(Integer companyId);
}

class ExpenseRepositoryImpl extends CustomRepositoryImpl implements ExpenseRepositoryCustom {
  private static final String SQL_SUM_VEHICLE = "select sum(value) from expense where vehicle_id=? and company_id=? and deleted=? and expense_type_id=? and date between ? and ?;";
  private static final String SQL_SUM_COMPANY = "select sum(value) from expense where company_id=? and deleted=? and expense_type_id=? and date between ? and ?;";

  @Override
  public BigDecimal sumValueByVehicleIdAndCompanyIdAndDeletedAndExpenseTypeAndDateBetween(Integer vehicleId, Integer companyId, Byte deleted, Integer expenseTypeId, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
	return (BigDecimal) entityManager.createNativeQuery(SQL_SUM_VEHICLE).setParameter(1, vehicleId).setParameter(2, companyId).setParameter(3, deleted).setParameter(4, expenseTypeId).
		  setParameter(5, startDate).setParameter(6, endDate).getSingleResult();
  }

  @Override
  public BigDecimal sumValueByCompanyIdAndDeletedAndExpenseTypeAndDateBetween(Integer companyId, Byte deleted, Integer expenseTypeId, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
	return (BigDecimal) entityManager.createNativeQuery(SQL_SUM_COMPANY).setParameter(1, companyId).setParameter(2, deleted).setParameter(3, expenseTypeId).
		  setParameter(4, startDate).setParameter(5, endDate).getSingleResult();
  }
}

interface LoggerRepositoryCustom {
  List<LoggerCompanyUserRole> getExtendedAll();

  List<LoggerCompanyUserRole> getExtendedByCompany(Integer companyId);
}

class LoggerRepositoryImpl extends CustomRepositoryImpl implements LoggerRepositoryCustom {

  private static final String SQL_GET_ALL = "select l.id, l.action_type, l.action_details, l.table_name, l.created, l.user_id, l.atomic," +
		" l.company_id,c.name as company_name,u.username,r.value as role from logger l inner join user u on l.user_id = u.id" +
		" inner join role r on u.role_id = r.id left join company c on l.company_id = c.id order by l.created desc;";

  private static final String SQL_GET_ALL_BY_COMPANY = "select l.id, l.action_type, l.action_details, l.table_name, l.created, l.user_id, l.atomic," +
		" l.company_id,c.name as company_name,u.username,r.value as role from logger l inner join user u on l.user_id = u.id" +
		" inner join role r on u.role_id = r.id inner join company c on l.company_id = c.id where l.company_id=? order by l.created desc";


  @Override
  public List<LoggerCompanyUserRole> getExtendedAll() {
	return entityManager.createNativeQuery(SQL_GET_ALL, "LoggerMapping").getResultList();
  }

  @Override
  public List<LoggerCompanyUserRole> getExtendedByCompany(Integer companyId) {
	return entityManager.createNativeQuery(SQL_GET_ALL_BY_COMPANY, "LoggerMapping").setParameter(1, companyId).getResultList();
  }
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
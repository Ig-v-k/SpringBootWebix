package com.project.webixs.logistic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@SqlResultSetMapping(
	  name = "UserMapping",
	  classes = @ConstructorResult(
			targetClass = User.class,
			columns = {
				  @ColumnResult(name = "id", type = Integer.class),
				  @ColumnResult(name = "username", type = String.class),
				  @ColumnResult(name = "password", type = String.class),
				  @ColumnResult(name = "first_name", type = String.class),
				  @ColumnResult(name = "last_name", type = String.class),
				  @ColumnResult(name = "registration_date", type = Date.class),
				  @ColumnResult(name = "email", type = String.class),
				  @ColumnResult(name = "role_id", type = Integer.class),
				  @ColumnResult(name = "status_id", type = Integer.class),
				  @ColumnResult(name = "company_id", type = Integer.class),
				  @ColumnResult(name = "notification_type_id", type = Integer.class),
				  @ColumnResult(name = "location_id", type = Integer.class),
			}
	  )
)
@Data
@Entity
@Table(name = "usr")
@NoArgsConstructor
//@AllArgsConstructor
public class User {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "username", length = 64)
  private String username;

  @Basic
//  @JsonIgnore
  @Column(name = "password", length = 128)
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
  @JsonIgnore
  @Column(name = "token", length = 64)
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
  @Column(name = "notification_type_id", nullable = false)
  private Integer notificationTypeId;

  @Basic
  @Column(name = "location_id")
  private Integer locationId;

  public User(Integer id, String password, String username, String firstName, String lastName, Date registrationDate, String email, Integer roleId, Integer statusId, Integer companyId, Integer notificationTypeId, Integer locationId) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.registrationDate = registrationDate == null ? null : new Timestamp(registrationDate.getTime());
	this.email = email;
	this.roleId = roleId;
	this.statusId = statusId;
	this.companyId = companyId;
	this.notificationTypeId = notificationTypeId;
	this.locationId = locationId;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	User user = (User) o;
	return Objects.equals(id, user.id);
  }

  @Override
  public String toString() {
	return "User{" +
		  "id=" + id +
		  ", username='" + username + '\'' +
		  ", firstName='" + firstName + '\'' +
		  ", lastName='" + lastName + '\'' +
		  ", registrationDate=" + registrationDate +
		  ", email='" + email + '\'' +
		  ", roleId=" + roleId +
		  ", statusId=" + statusId +
		  ", companyId=" + companyId +
		  ", notificationTypeId=" + notificationTypeId +
		  ", locationId=" + locationId +
		  '}';
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
  }
}
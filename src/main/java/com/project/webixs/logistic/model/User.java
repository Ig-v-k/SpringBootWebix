package com.project.webixs.logistic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@SqlResultSetMappings({
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
//						@ColumnResult(name = "token", type = String.class),
						@ColumnResult(name = "role_id", type = Integer.class),
						@ColumnResult(name = "status_id", type = Integer.class),
						@ColumnResult(name = "company_id", type = Integer.class),
						@ColumnResult(name = "notification_type_id", type = Integer.class),
						@ColumnResult(name = "location_id", type = Integer.class)
				  }
			)
	  ),
	  @SqlResultSetMapping(
			name = "UserMappingTest",
			classes = @ConstructorResult(
				  targetClass = User.class,
				  columns = {
						@ColumnResult(name = "username", type = String.class),
						@ColumnResult(name = "first_name", type = String.class),
						@ColumnResult(name = "last_name", type = String.class),
				  }
			)
	  )
})
@Data
@Entity
@NoArgsConstructor
@Table(name = "usr")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {
	  "id",
	  "username",
	  "password",
	  "firstName",
//	  "token",
	  "lastName",
	  "registrationDate",
	  "email",
	  "roleId",
	  "statusId",
	  "companyId",
	  "notificationTypeId",
	  "locationId",
	  "userPayments"
})
//@AllArgsConstructor
public class User implements Serializable {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Basic
  @Column(name = "username", length = 64)
  private String username;
  @Basic
  @JsonIgnore
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
  //  @Basic
//  @JsonIgnore
//  @Column(name = "token", length = 64)
//  private String token;
  @Basic
  @Column(name = "notification_type_id", nullable = false)
  private Integer notificationTypeId;
  @Basic
  @Column(name = "location_id")
  private Integer locationId;
//  @JsonIgnore
  @OneToMany(mappedBy = "paymentUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Payment> userPayments;

  public User(Integer id,
			  String password,
			  String username,
			  String firstName,
			  String lastName,
			  Date registrationDate,
			  String email,
			  Integer roleId,
			  Integer statusId,
			  Integer companyId,
			  Integer notificationTypeId,
			  Integer locationId) {
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
}
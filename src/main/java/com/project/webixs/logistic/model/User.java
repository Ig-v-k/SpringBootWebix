package com.project.webixs.logistic.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "usr")
public class User {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "username", length = 64)
  private String username;

  @Basic
  @Column(name = "password", length = 128)
  private String password;

  @Basic
  @Column(name = "first_name", length = 64)
  private String firstName;

  @Basic
  @Column(name = "last_name", length = 64)
  private String lastName;

  @Basic
  @Column(name = "photo")
  private byte[] photo;

  @Basic
  @Column(name = "registration_date", nullable = false)
  private Timestamp registrationDate;

  @Basic
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
  @Column(name = "location_id", nullable = false)
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
package com.project.webixs.logistic.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "role")
public class Role {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "name", nullable = false, length = 64)
  private String name;

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
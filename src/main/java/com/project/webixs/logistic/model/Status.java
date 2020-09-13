package com.project.webixs.logistic.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "status")
public class Status {

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
	Status status = (Status) o;
	return Objects.equals(id, status.id);
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
  }
}
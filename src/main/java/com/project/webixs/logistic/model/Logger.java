package com.project.webixs.logistic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Logger {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic
  @Column(name = "action_type", nullable = false, length = 128)
  private String actionType;

  @Basic
  @Column(name = "action_details", nullable = false, length = 128)
  private String actionDetails;

  @Basic
  @Column(name = "table_name", nullable = false, length = 128)
  private String tableName;

  @Basic
  @Column(name = "created", nullable = false)
  private Timestamp created;

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
	return Objects.equals(id, logger.id);
  }

  @Override
  public int hashCode() {
	return Objects.hash(id);
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
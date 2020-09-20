package com.project.webixs.logistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Logger {
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
  private Timestamp created;

  @Basic
  @Column(name = "atomic", nullable = false)
  private Byte atomic;

  @Basic
  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Basic
  @Column(name = "company_id")
  private Integer companyId;

  public Logger(Integer userId, String actionType, String actionDetails, String tableName, Byte atomic, Integer companyId) {
	this.userId = userId;
	this.actionType = actionType;
	this.actionDetails = actionDetails;
	this.tableName = tableName;
	this.atomic = atomic;
	this.companyId = companyId;
  }

  public Logger(Integer id, String actionType, String actionDetails, String tableName, Date created, Byte atomic, Integer userId, Integer companyId) {
	this.id = id;
	this.actionType = actionType;
	this.actionDetails = actionDetails;
	this.tableName = tableName;
	this.created = created==null ? null:new Timestamp(created.getTime());
	this.atomic = atomic;
	this.userId = userId;
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
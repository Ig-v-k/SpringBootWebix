package com.project.webixs.logistic.model.modelCustom;

import com.project.webixs.logistic.model.Logger;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.util.Date;

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
@Data
@MappedSuperclass
@NoArgsConstructor
public class LoggerCompanyUserRole extends Logger implements Serializable {

  private String companyName;
  private String username;
  private String role;

  public LoggerCompanyUserRole(Integer id, String actionType, String actionDetails, String tableName, Date created, Byte atomic, Integer userId, Integer companyId, String companyName, String username, String role) {
	super(id, actionType, actionDetails, tableName, created, atomic, userId, companyId);
	this.companyName = companyName;
	this.username = username;
	this.role = role;
  }
}
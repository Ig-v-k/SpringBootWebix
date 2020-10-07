package com.project.webixs.logistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@SqlResultSetMapping(
	  name = "PaymentMapping",
	  classes = @ConstructorResult(
			targetClass = Payment.class,
			columns = {
				  @ColumnResult(name = "id", type = Integer.class),
				  @ColumnResult(name = "status", type = String.class),
				  @ColumnResult(name = "pay_date", type = Date.class),
				  @ColumnResult(name = "method", type = String.class),
				  @ColumnResult(name = "number", type = String.class),
				  @ColumnResult(name = "type", type = Integer.class),
				  @ColumnResult(name = "sum", type = String.class),
				  @ColumnResult(name = "left_pay", type = String.class),
				  @ColumnResult(name = "name", type = String.class),
				  @ColumnResult(name = "city", type = String.class),
				  @ColumnResult(name = "country", type = String.class)
			}
	  )
)
@Data
@Entity
@NoArgsConstructor
@Table(name = "payment")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {
	  "id",
	  "status",
	  "date",
	  "method",
	  "number",
	  "type",
	  "sum",
	  "leftPay",
	  "name",
	  "city",
	  "country",
	  "paymentUser"
})
public class Payment implements Serializable {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id;
  @Basic
  @Column(name = "status", length = 32, nullable = false)
  @JsonProperty("status")
  private String status;
  @Basic
  @Column(name = "pay_date", length = 64, nullable = false)
  @JsonProperty("pay_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM, HH:mm", locale = "en")
  private Timestamp date;
  @Basic
  @Column(name = "method", length = 64, nullable = false)
  @JsonProperty("method")
  private String method;
  @Basic
  @Column(name = "number", length = 64, nullable = false)
  @JsonProperty("_number")
  private String number;
  @Basic
  @Column(name = "type", nullable = false)
  @JsonProperty("type")
  private Integer type;
  @Basic
  @Column(name = "sum", length = 64, nullable = false)
  @JsonProperty("sum")
  private String sum;
  @Basic
  @Column(name = "left_pay", length = 64, nullable = false)
  @JsonProperty("leftPay")
  private String leftPay;
  @Basic
  @Column(name = "name", length = 64, nullable = false)
  @JsonProperty("name")
  private String name;
  @Basic
  @Column(name = "city", length = 64, nullable = false)
  @JsonProperty("city")
  private String city;
  @Basic
  @Column(name = "country", length = 64, nullable = false)
  @JsonProperty("country")
  private String country;
  @ManyToOne
  @JsonProperty("paymentUser")
  @JsonManagedReference
  private User paymentUser;

  public Payment(final Integer id,
				 final String status,
				 final Date date,
				 final String method,
				 final String number,
				 final Integer type,
				 final String sum,
				 final String leftPay,
				 final String name,
				 final String city,
				 final String country,
				 final User paymentUser) {
	this.id = id;
	this.status = status;
	this.date = date == null ? null : new Timestamp(date.getTime());
	this.method = method;
	this.number = number;
	this.type = type;
	this.sum = sum;
	this.leftPay = leftPay;
	this.name = name;
	this.city = city;
	this.country = country;
	this.paymentUser = paymentUser;
  }
}

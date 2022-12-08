package com.project.bookshop.model;

import java.io.Serializable;     
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.project.bookshop.type.PostgreSqlEnum;
import com.project.bookshop.type.UserRole;

import lombok.ToString;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="userr")
@ToString
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSqlEnum.class
	)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
    
	@NotEmpty
	@NotNull
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	@NotNull
	@Past(message="date of birth must be less than today")
	@DateTimeFormat( pattern="yyyy-MM-dd")
	private Date dateOfBirth;
    
	@Email(message="email invalid format!")
	private String email;

	@Column(name="first_name")
	@Pattern(regexp= "^[A-Z][a-z]+$")
	@NotNull
	private String firstName;
	
	@Pattern(regexp= "^[A-Z][a-z]+$")
	@NotNull
	@Column(name="last_name")
	private String lastName;
 
	@NotNull
	@NotBlank
	@Size(min=5)
	private String password;
	

    @Type(type="pgsql_enum")
	@Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserRole role;
    @NotNull
	private String username;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user",cascade={CascadeType.REMOVE})
	@JsonManagedReference
	private List<Order> orders;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

}
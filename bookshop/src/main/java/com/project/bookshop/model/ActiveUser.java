package com.project.bookshop.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the active_user database table.
 * 
 */
@Entity
@Table(name="active_user")
public class ActiveUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="active_user_id")
	private Integer activeUserId;

	@Column(name="jwt_token")
	private String jwtToken;
    
	@OneToOne
	@JoinColumn(name="user_id")
	private User userId;

	public ActiveUser() {
	}

	
	
	
	
	
	public ActiveUser(String jwtToken, User userId) {
	
		this.jwtToken = jwtToken;
		this.userId = userId;
	}






	public Integer getActiveUserId() {
		return this.activeUserId;
	}

	public void setActiveUserId(Integer activeUserId) {
		this.activeUserId = activeUserId;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public User getUserId() {
		return this.userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

}
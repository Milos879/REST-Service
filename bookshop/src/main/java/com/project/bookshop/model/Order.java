package com.project.bookshop.model;

import java.io.Serializable; 
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the orderr database table.
 * 
 */
@Entity
@Table(name="orderr")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book bookId;

	@Column(name="order_at")
	private Timestamp orderAt;

	//bi-directional many-to-one association to Userr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;

	public Order() {
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Book getBookId() {
		return this.bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}

	public Timestamp getOrderAt() {
		return this.orderAt;
	}

	public void setOrderAt(Timestamp orderAt) {
		this.orderAt = orderAt;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetails1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_details_id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@OneToOne
	@JoinColumn(name = "order_id")
	private Orders orders;

	private int quanity;

	@JsonIgnore
	private String userName;

	public OrderDetails1() {
		super();
	}

	public OrderDetails1(Product product, Orders orders, String userName, int quanity) {
		super();
		this.product = product;
		this.orders = orders;
		this.userName = userName;
		this.quanity = quanity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

}

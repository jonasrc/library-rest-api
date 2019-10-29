package com.atividade.library.domain;

import java.util.UUID;

import com.atividade.library.util.Util;

public class Order {
	private String id;
	
	private ShoppingCart shoppingCart;
	
	private Double totalPrice;
	
	private String status;
	
	private String creationDate;
	
	private static final String pendingStatus = "pending";
	
	public Order(ShoppingCart shoppingCart, Double totalPrice) {
		this.id = UUID.randomUUID().toString();
		this.shoppingCart = shoppingCart;
		this.totalPrice = totalPrice;
		this.status = pendingStatus;
		this.creationDate = Util.getCurrentDateTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String created_at) {
		this.creationDate = created_at;
	}
}

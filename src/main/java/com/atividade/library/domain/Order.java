package com.atividade.library.domain;

import java.util.UUID;

public class Order {
	private String id;
	
	private ShoppingCart shoppingCart;
	
	private Double totalPrice;
	
	private String status;
	
	private static final String pendingStatus = "Pendente";
	
	public Order(ShoppingCart shoppingCart, Double totalPrice) {
		this.id = UUID.randomUUID().toString();
		this.shoppingCart = shoppingCart;
		this.totalPrice = totalPrice;
		this.status = pendingStatus;
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
}

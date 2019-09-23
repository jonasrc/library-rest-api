package com.atividade.library.domain;

import java.util.UUID;

public class Order {
	private String id;
	
	private ShoppingCart shoppingCart;
	
	private Double totalPrice;
	
	public Order(ShoppingCart shoppingCart) {
		this.id = UUID.randomUUID().toString();
		this.shoppingCart = shoppingCart;
		this.totalPrice = calculateTotalPrice(shoppingCart);
	}
	
	private Double calculateTotalPrice(ShoppingCart shoppingCart) {
		return 100.00;
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
}

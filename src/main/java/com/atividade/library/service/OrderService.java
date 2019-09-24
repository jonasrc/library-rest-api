package com.atividade.library.service;

import java.util.List;

import com.atividade.library.domain.Order;

public interface OrderService {
	public Order getById(String id);
	
	public List<Order> getList();
	
	public Order create(String shoppingCartId);
}

package com.atividade.library.service;

import com.atividade.library.domain.Order;
import com.atividade.library.domain.ShoppingCart;

public interface OrderService {
	public Order getOrder(String id);
	
	public Order postOrder(ShoppingCart shoppingCart);
}

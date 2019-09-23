package com.atividade.library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.library.domain.Order;
import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.service.OrderService;
import com.atividade.library.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	public Order getOrder(String id) {
		return new Order(shoppingCartService.getShoppingCart());
	}
	
	public Order postOrder(ShoppingCart shoppingCart) {
		return new Order(shoppingCart);
	}
}

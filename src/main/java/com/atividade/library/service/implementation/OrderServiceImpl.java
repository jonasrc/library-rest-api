package com.atividade.library.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.library.domain.Order;
import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.exception.OrderNotFoundException;
import com.atividade.library.exception.ShoppingCartIsEmptyException;
import com.atividade.library.service.OrderService;
import com.atividade.library.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private List<Order> orderList;
	
	@PostConstruct
	private void buildMockList() {
		this.orderList = new ArrayList<>();
		
		shoppingCartService.getList().stream().forEach(
			element -> this.orderList.add(new Order(element, calculateTotalPrice(element))
		));
	}
	
	@Override
	public Order getById(String id) {
		return findOrFail(id);
	}
	
	@Override
	public List<Order> getList() {
		return this.orderList;
	}
	
	@Override
	public Order create(String shoppingCartId) {
		ShoppingCart shoppingCart = shoppingCartService.getById(shoppingCartId);
		
		if(shoppingCart.getBookList().isEmpty()) {
			throw new ShoppingCartIsEmptyException(shoppingCartId);
		}
		
		return new Order(shoppingCart, calculateTotalPrice(shoppingCart));
	}
	
	public Order findOrFail(String id) {
		Order order = orderList.stream().filter(element -> element.getId().equals(id)).findAny().orElse(null);
		
		if(order == null) {
			throw new OrderNotFoundException(id);
		}
		
		return order;
	}
	
	private Double calculateTotalPrice(ShoppingCart shoppingCart) {
		return shoppingCart.getBookList().stream().map(element -> element.getPrice()).mapToDouble(Double::doubleValue).sum();
	}
}

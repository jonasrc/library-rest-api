package com.atividade.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.Order;
import com.atividade.library.service.OrderService;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getById(@PathVariable String id){
		return ResponseEntity.ok().body(orderService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getList(){
		return ResponseEntity.ok().body(orderService.getList());
	}
	
	@PostMapping
	public ResponseEntity<Order> create(@RequestParam String shoppingCartId){
		Order order = orderService.create(shoppingCartId);
		return ResponseEntity.created(URI.create(order.getId())).body(order);
	}
}

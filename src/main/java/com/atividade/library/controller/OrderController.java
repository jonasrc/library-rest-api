package com.atividade.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.Order;
import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.service.OrderService;
import com.atividade.library.service.ShoppingCartService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable String id){
		return ResponseEntity.ok().body(orderService.getOrder(id));
	}
	
	@PostMapping
	public ResponseEntity<Order> postOrder(){
		return ResponseEntity.ok().body(orderService.postOrder());
	}
}

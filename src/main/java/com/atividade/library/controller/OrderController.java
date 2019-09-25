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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/order")
@Api(tags = "Pedidos de compra", value = "orderController", description="Criação, busca e listagem de pedidos de compras")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar pedido de compra a partir de seu ID")
	public ResponseEntity<Order> getById(@PathVariable String id){
		return ResponseEntity.ok().body(orderService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de pedidos de compras")
	public ResponseEntity<List<Order>> getList(){
		return ResponseEntity.ok().body(orderService.getList());
	}
	
	@PostMapping
    @ApiOperation(value = "Criar novo pedido de compra")
	public ResponseEntity<Order> create(@RequestParam String shoppingCartId){
		Order order = orderService.create(shoppingCartId);
		return ResponseEntity.created(URI.create(order.getId())).body(order);
	}
}

package com.atividade.library.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.atividade.library.util.Audit;
import com.atividade.library.util.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atividade.library.domain.Order;
import com.atividade.library.exception.NotAuthorizedException;
import com.atividade.library.service.OrderService;
import com.atividade.library.util.Authentication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/order")
@Api(tags = "Pedidos de compra", value = "orderController", description="Criação, busca e listagem de pedidos de compras")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar pedido de compra a partir de seu ID")
	public ResponseEntity<Order> getById(
			HttpServletRequest request,
            @ApiParam(value = "ID do pedido de compra a ser buscado.", required = true)
            @PathVariable String id) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		return ResponseEntity.ok().body(orderService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de pedidos de compras")
	public ResponseEntity<List<Order>> getList(HttpServletRequest request) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		return ResponseEntity.ok().body(orderService.getList());
	}
	
	@PostMapping
    @ApiOperation(value = "Criar novo pedido de compra")
	public ResponseEntity<Order> create(
			HttpServletRequest request,
            @ApiParam(value = "ID do carrinho de compras a ser usado na criação do pedido de compras.", required = true)
            @RequestParam String shoppingCartId,
			@ApiParam(value = "Número do cartão de crédito", required = true)
			@RequestBody String creditCardHolder,
			@ApiParam(value = "Número do cartão de crédito", required = true)
			@RequestBody String creditCardNumber,
			@ApiParam(value = "Expiração do cartão de crédito", required = true)
			@RequestBody String creditCardExpirationDate,
			@ApiParam(value = "Código de segurança do cartão de crédito", required = true)
			@RequestBody String creditCardCVC) throws NotAuthorizedException, IOException, URISyntaxException {
//		Authenticating user
		String userId = Authentication.authenticateUser(request);

//		Creating order
		Order order = orderService.create(shoppingCartId);

//		Creating credit card purchase
		String transactionId = Transaction.postPurchase(order.getId(), userId, creditCardHolder, creditCardNumber, creditCardExpirationDate, creditCardCVC);

//		Creating audit log
		Audit.postLog(order.getId(), userId, transactionId, order.getCreationDate());

		return ResponseEntity.created(URI.create(order.getId())).body(order);
	}
}

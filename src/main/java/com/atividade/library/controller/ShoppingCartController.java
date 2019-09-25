package com.atividade.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.service.ShoppingCartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/shopping-cart")
@Api(tags = "Carrinho de compra", value = "shoppingCartController", description="Listagem, busca, adição e remoção de livros a carrinhos de compras")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar carrinho de compras a partir de seu ID")
	public ResponseEntity<ShoppingCart> getById(@PathVariable String id){
		return ResponseEntity.ok().body(shoppingCartService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de carrinhos de compras")
	public ResponseEntity<List<ShoppingCart>> getList(){
		return ResponseEntity.ok().body(shoppingCartService.getList());
	}
	
	@PostMapping
	@ApiOperation(value = "Criar carrinho de compras")
	public ResponseEntity<ShoppingCart> create(@RequestParam(required = false) String bookId){
		ShoppingCart shoppingCart = shoppingCartService.create(bookId);
		return ResponseEntity.created(URI.create(shoppingCart.getId())).body(shoppingCart);
	}
	
	@PutMapping("/{shoppingCartId}/book/{bookId}")
    @ApiOperation(value = "Adicionar livro a um carrinho de compras")
	public ResponseEntity<ShoppingCart> addBook(@PathVariable String shoppingCartId, @PathVariable String bookId){
		ShoppingCart shoppingCart = shoppingCartService.addBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
	
	@DeleteMapping("/{shoppingCartId}/book/{bookId}")
    @ApiOperation(value = "Remover livro de um carrinho de compras")
	public ResponseEntity<ShoppingCart> removeBook(@PathVariable String shoppingCartId, @PathVariable String bookId){
		ShoppingCart shoppingCart = shoppingCartService.removeBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
}

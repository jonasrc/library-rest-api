package com.atividade.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.service.ShoppingCartService;

@RestController
@RequestMapping(value = "/api/shopping-cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCart> getById(@PathVariable String id){
		return ResponseEntity.ok().body(shoppingCartService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ShoppingCart>> getList(){
		return ResponseEntity.ok().body(shoppingCartService.getList());
	}
	
	@PutMapping("{shoppingCartId}/book/{bookId}")
	public ResponseEntity<ShoppingCart> addBook(@PathVariable String shoppingCartId, @PathVariable String bookId){
		ShoppingCart shoppingCart = shoppingCartService.addBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
	
	@DeleteMapping("{shoppingCartId}/book/{bookId}")
	public ResponseEntity<ShoppingCart> removeBook(@PathVariable String shoppingCartId, @PathVariable String bookId){
		ShoppingCart shoppingCart = shoppingCartService.removeBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
}

package com.atividade.library.controller;

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
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping
	public ResponseEntity<ShoppingCart> getCart(){
		return ResponseEntity.ok().body(shoppingCartService.getShoppingCart());
	}
	
	@PutMapping("/book/{bookId}")
	public ResponseEntity<ShoppingCart> addBook(@PathVariable String bookId){
		shoppingCartService.addBook(bookId);
		return ResponseEntity.ok().body(shoppingCartService.getShoppingCart());
	}
	
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<ShoppingCart> removeBook(@PathVariable String bookId){
		shoppingCartService.removeBook(bookId);
		return ResponseEntity.ok().body(shoppingCartService.getShoppingCart());
	}
}

package com.atividade.library.service;

import java.util.List;

import com.atividade.library.domain.ShoppingCart;

public interface ShoppingCartService {
	public ShoppingCart getById(String id);
	
	public List<ShoppingCart> getList();
	
	public ShoppingCart addBook(String shoppingCartId, String bookId);
	
	public ShoppingCart removeBook(String shoppingCartId, String bookId);
}

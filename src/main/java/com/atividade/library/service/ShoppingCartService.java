package com.atividade.library.service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.ShoppingCart;

public interface ShoppingCartService {
	public ShoppingCart getShoppingCart();
	
	public ShoppingCart addBook(String bookId);
	
	public ShoppingCart removeBook(String bookId);
}

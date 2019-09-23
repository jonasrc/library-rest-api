package com.atividade.library.service.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.service.BookService;
import com.atividade.library.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCart mockShoppingCart;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public ShoppingCart getShoppingCart() {
		return mockShoppingCart;
	}

	@Override
	public ShoppingCart addBook(String bookId) {
		Book book = bookService.findBookOrFail(bookId);
		
		Optional<Book> bookOnShoppingCartList = getBookFromShoppingCartList(bookId);
		if(bookOnShoppingCartList.isPresent()) {
			throw new NoSuchElementException("Book with id \"" + bookId + "\" already on shopping cart.");
		}
		
		this.mockShoppingCart.addBook(book);
		return this.mockShoppingCart;
	}

	@Override
	public ShoppingCart removeBook(String bookId) {
		Book book = bookService.findBookOrFail(bookId);
		
		Optional<Book> bookOnShoppingCartList = getBookFromShoppingCartList(bookId);
		if(!bookOnShoppingCartList.isPresent()) {
			throw new NoSuchElementException("Book with id \"" + bookId + "\" not found on shopping cart.");
		}
		
		this.mockShoppingCart.removeBook(book);
		return this.mockShoppingCart;
	}
	
	private Optional<Book> getBookFromShoppingCartList(String bookId) {
		return this.mockShoppingCart
				.getBookList()
				.stream()
				.filter(element -> element.getId().equals(bookId))
				.findFirst(); 
	}
	
	@PostConstruct
	public void createMockShoppingCart() {
		Book book = bookService.getRandomBook();
		this.mockShoppingCart = new ShoppingCart(book);
	}
}

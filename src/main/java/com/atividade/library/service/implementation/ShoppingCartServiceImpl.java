package com.atividade.library.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.exception.BookNotFoundOnShoppingCartException;
import com.atividade.library.exception.ShoppingCartNotFoundException;
import com.atividade.library.service.BookService;
import com.atividade.library.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private BookService bookService;

	private List<ShoppingCart> mockShoppingCartList;
	
	@PostConstruct
	public void createMockShoppingCartList() {
		this.mockShoppingCartList = new ArrayList<>();
		this.mockShoppingCartList.add(new ShoppingCart(bookService.getRandom()));
		this.mockShoppingCartList.add(new ShoppingCart(bookService.getRandom()));
		this.mockShoppingCartList.add(new ShoppingCart(bookService.getRandom()));
	}
	
	@Override
	public ShoppingCart getById(String id) {
		return findOrFail(id);
	}
	
	@Override
	public List<ShoppingCart> getList() {
		return this.mockShoppingCartList;
	}
	
	@Override
	public ShoppingCart create(String bookId) {
		if(bookId != null) {
			Book book = bookService.getById(bookId);
			return new ShoppingCart(book);
		}
		
		return new ShoppingCart();
	}

	@Override
	public ShoppingCart addBook(String shoppingCartId, String bookId) {
		// Getting shopping cart from list
		ShoppingCart shoppingCart = findOrFail(shoppingCartId);
		
		// Getting book from book list
		Book book = bookService.getById(bookId);
		
		// Getting book list from shopping cart
		List<Book> shoppingCartBookList = shoppingCart.getBookList();
		
		// Adding book and returning resulting shopping cart
		shoppingCartBookList.add(book);
		return shoppingCart;
	}

	@Override
	public ShoppingCart removeBook(String shoppingCartId, String bookId) {
		// Getting shopping cart from list
		ShoppingCart shoppingCart = findOrFail(shoppingCartId);
		
		// Getting book from book list
		Book book = bookService.getById(bookId);
		
		// Getting book list from shopping cart
		List<Book> shoppingCartBookList = shoppingCart.getBookList();
		
		// Asserting that the book is on the shopping cart list
		if(!shoppingCartBookList
				.stream()
				.filter(element -> element.getId().equals(bookId))
				.findAny()
				.isPresent()) {
			throw new BookNotFoundOnShoppingCartException(bookId);
		}
		
		// Removing book and returning resulting shopping cart
		shoppingCartBookList.remove(book);
		return shoppingCart;
	}
	
	private ShoppingCart findOrFail(String id) {
		ShoppingCart shoppingCart = this.mockShoppingCartList
				.stream()
				.filter(element -> element.getId().equals(id))
				.findAny()
				.orElse(null);
		
		if(shoppingCart == null) {
			throw new ShoppingCartNotFoundException(id);
		}
		
		return shoppingCart;
	}
}

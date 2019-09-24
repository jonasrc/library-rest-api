package com.atividade.library.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCart {
	private String id;
	
	@Autowired
	private List<Book> bookList = new ArrayList<>();
	
	public ShoppingCart(Book book) {
		this.id = UUID.randomUUID().toString();
		this.bookList.add(book);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}

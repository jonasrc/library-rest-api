package com.atividade.library.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.atividade.library.util.Util;

public class ShoppingCart {
	private String id;
	
	@Autowired
	private List<Book> bookList = new ArrayList<>();
	
	private String created_at;
	
	public ShoppingCart() {
		this.id = UUID.randomUUID().toString();
		this.created_at = Util.getCurrentDateTime();
	}
	
	public ShoppingCart(Book book) {
		this.id = UUID.randomUUID().toString();
		this.bookList.add(book);
		this.created_at = Util.getCurrentDateTime();
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}

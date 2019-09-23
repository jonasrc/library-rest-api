package com.atividade.library.domain;

import java.util.UUID;

public class Comment {
	private String id;
	
	private Book book;
	
	private String text;
	
	private String author;
	
	public Comment(Book book, String text, String author) {
		this.id = UUID.randomUUID().toString();
		this.book = book;
		this.text = text;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}

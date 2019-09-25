package com.atividade.library.domain;

import java.util.UUID;

import com.atividade.library.util.Util;

public class Book {
	private String id;
	
	private String title;
	
	private String author;
	
	private String edition;
	
	private Double price;
	
	private String created_at;
	
	public Book(String title, String author, String edition, Double price) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.price = price;
		this.created_at = Util.getCurrentDateTime();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}

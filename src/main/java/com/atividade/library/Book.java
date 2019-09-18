package com.atividade.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.*;

@Component
public class Book implements BookInterface {
	@Autowired
	private Integer id;

	@Autowired
	private String title;

	@Autowired
	private String author;

	@Autowired
	private String edition;
	
	public Book(Integer id, String title, String author, String edition) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.edition = edition;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String getEdition() {
		return edition;
	}

	@Override
	public void setEdition(String edition) {
		this.edition = edition;
	}
}

package com.atividade.library.service;

import java.util.List;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;

public interface BookService {
	public Book getById(String id);
	
	public List<Book> getList(String title, String author, String edition, Double price);
	
	public Book create(String title, String author, String edition, Double price);
	
	public Book getRandom();

	public Comment postComment(String bookId, String text, String author);
}

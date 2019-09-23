package com.atividade.library.service;

import java.util.List;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;

public interface BookService {
	public Book postBook(String title, String author, String edition, Double price);

	public Book findBook(String id);

	public Book findBookOrFail(String id);
	
	public Book getRandomBook();
	
	public List<Book> searchBooks(String title, String author, String edition, Double price);

	public Comment postComment(String bookId, String text, String author);
}

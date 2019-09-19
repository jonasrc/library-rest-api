package com.atividade.library.service;

import com.atividade.library.model.Book;

public interface BookService {
	public Book postBook(String title, String author, String edition);
}

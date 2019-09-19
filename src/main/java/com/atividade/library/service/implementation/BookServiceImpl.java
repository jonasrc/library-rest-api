package com.atividade.library.service.implementation;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.atividade.library.model.Book;
import com.atividade.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Bean
	@Override
	public Book postBook(String title, String author, String edition) {
		String bookId = UUID.randomUUID().toString();
		return new Book(bookId, title, author, edition);
	}
}

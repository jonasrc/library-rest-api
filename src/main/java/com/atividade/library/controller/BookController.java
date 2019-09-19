package com.atividade.library.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.model.Book;
import com.atividade.library.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/its-alive")
	public ResponseEntity<String> itsAlive() {
		return ResponseEntity.ok("It's alive!");
	}
	
	@GetMapping("/book")
	public ResponseEntity<Book> getBook() {
		Book book = bookService.postBook();
		return ResponseEntity.ok(book);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> postBook(@RequestBody Book bookParams) {
		Book book = bookService.postBook(bookParams);
		return ResponseEntity.created(URI.create(book.getId())).build();
	}
}

package com.atividade.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;
import com.atividade.library.service.BookService;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable String id) {
		return ResponseEntity.ok().body(bookService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getList(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String edition,
			@RequestParam(required = false) Double price) {
		List<Book> bookList = bookService.getList(title, author, edition, price);
		return ResponseEntity.ok().body(bookList);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(
			@RequestParam String title,
			@RequestParam String author,
			@RequestParam String edition,
			@RequestParam Double price) {
		Book book = bookService.create(title, author, edition, price);
		return ResponseEntity.created(URI.create(book.getId())).body(book);
	}
	
	@PostMapping("/{bookId}/comment")
	public ResponseEntity<Comment> postComment(
			@PathVariable String bookId, 
			@RequestParam String text, 
			@RequestParam String author) {
		Comment comment = bookService.postComment(bookId, text, author);
		return ResponseEntity.created(URI.create(comment.getId())).body(comment);
	}
}

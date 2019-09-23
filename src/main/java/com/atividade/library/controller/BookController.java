package com.atividade.library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;
import com.atividade.library.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book bookParams) {
		Book book = bookService.postBook(
			bookParams.getTitle(),
			bookParams.getAuthor(),
			bookParams.getEdition(),
			bookParams.getPrice());
		return ResponseEntity.created(URI.create(book.getId())).body(book);
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> searchBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String edition,
			@RequestParam(required = false) Double price) {
		List<Book> bookList = bookService.searchBooks(title, author, edition, price);
		return ResponseEntity.ok().body(bookList);
	}
	
	@PostMapping("/{bookId}/comment")
	public ResponseEntity<Comment> createComment(
			@PathVariable String bookId, 
			@RequestParam String text, 
			@RequestParam String author) {
		Comment comment = bookService.postComment(bookId, text, author);
		return ResponseEntity.created(URI.create(comment.getId())).body(comment);
	}
}

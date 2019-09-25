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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/book")
@Api(tags = "Livros", value = "bookController")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar livro a partir de seu ID")
	public ResponseEntity<Book> getById(@PathVariable String id) {
		return ResponseEntity.ok().body(bookService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de livros")
	public ResponseEntity<List<Book>> getList(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String edition,
			@RequestParam(required = false) Double price) {
		List<Book> bookList = bookService.getList(title, author, edition, price);
		return ResponseEntity.ok().body(bookList);
	}
	
	@PostMapping
    @ApiOperation(value = "Cadastrar livro")
	public ResponseEntity<Object> create(
			@RequestParam String title,
			@RequestParam String author,
			@RequestParam String edition,
			@RequestParam Double price) {
		Book book = bookService.create(title, author, edition, price);
		return ResponseEntity.created(URI.create(book.getId())).body(book);
	}
	
	@PostMapping("/{bookId}/comment")
    @ApiOperation(value = "Postar comentário em um livro")
	public ResponseEntity<Comment> postComment(
			@PathVariable String bookId, 
			@RequestParam String text, 
			@RequestParam String author) {
		Comment comment = bookService.postComment(bookId, text, author);
		return ResponseEntity.created(URI.create(comment.getId())).body(comment);
	}
}

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
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/book")
@Api(tags = "Livros", value = "bookController", description="Cadastro, listagem e adição de comentários a livros")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar livro a partir de seu ID")
	public ResponseEntity<Book> getById(
            @ApiParam(value = "ID do livro a ser buscado", required = true)
			@PathVariable String id) {
		return ResponseEntity.ok().body(bookService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de livros")
	public ResponseEntity<List<Book>> getList(
            @ApiParam(value = "Filtrar busca por título", required = false)
			@RequestParam(required = false) String title,
            @ApiParam(value = "Filtrar busca por autor", required = false)
			@RequestParam(required = false) String author,
            @ApiParam(value = "Filtrar busca por edição", required = false)
			@RequestParam(required = false) String edition,
            @ApiParam(value = "Filtrar busca por preço", required = false)
			@RequestParam(required = false) Double price) {
		List<Book> bookList = bookService.getList(title, author, edition, price);
		return ResponseEntity.ok().body(bookList);
	}
	
	@PostMapping
    @ApiOperation(value = "Cadastrar livro")
	public ResponseEntity<Object> create(
            @ApiParam(value = "Título do livro a ser cadastrado", required = true)
			@RequestParam String title,
            @ApiParam(value = "Autor do livro a ser cadastrado", required = true)
			@RequestParam String author,
            @ApiParam(value = "Edição do livro a ser cadastrado", required = true)
			@RequestParam String edition,
            @ApiParam(value = "Preço do livro a ser cadastrado", required = true)
			@RequestParam Double price) {
		Book book = bookService.create(title, author, edition, price);
		return ResponseEntity.created(URI.create(book.getId())).body(book);
	}
	
	@PostMapping("/{bookId}/comment")
    @ApiOperation(value = "Postar comentário em um livro")
	public ResponseEntity<Comment> postComment(
            @ApiParam(value = "ID do livro ao qual o comentário se refere", required = true)
			@PathVariable String bookId, 
            @ApiParam(value = "Texto do comentário", required = true)
			@RequestParam String text, 
            @ApiParam(value = "Autor do comentário", required = true)
			@RequestParam String author) {
		Comment comment = bookService.postComment(bookId, text, author);
		return ResponseEntity.created(URI.create(comment.getId())).body(comment);
	}
}

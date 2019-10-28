package com.atividade.library.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.library.domain.ShoppingCart;
import com.atividade.library.exception.NotAuthorizedException;
import com.atividade.library.service.ShoppingCartService;
import com.atividade.library.util.Authentication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/shopping-cart")
@Api(tags = "Carrinho de compra", value = "shoppingCartController", description="Criação, listagem e busca de carrinhos de compra, e adição / remoção de livros de carrinhos de compras")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "Buscar carrinho de compras a partir de seu ID")
	public ResponseEntity<ShoppingCart> getById(
			HttpServletRequest request,
            @ApiParam(value = "ID do carrinho de compras a ser buscado.", required = true)
            @PathVariable String id) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		return ResponseEntity.ok().body(shoppingCartService.getById(id));
	}
	
	@GetMapping
    @ApiOperation(value = "Buscar lista de carrinhos de compras")
	public ResponseEntity<List<ShoppingCart>> getList(HttpServletRequest request) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		return ResponseEntity.ok().body(shoppingCartService.getList());
	}
	
	@PostMapping
	@ApiOperation(value = "Criar carrinho de compras")
	public ResponseEntity<ShoppingCart> create(
			HttpServletRequest request,
            @ApiParam(value = "ID do livro a ser adicionado ao novo carrinho de compras. O parâmetro é opcional, e caso não seja passado, o carrinho será criado vazio.", required = false)
			@RequestParam(required = false) String bookId) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		ShoppingCart shoppingCart = shoppingCartService.create(bookId);
		return ResponseEntity.created(URI.create(shoppingCart.getId())).body(shoppingCart);
	}
	
	@PutMapping("/{shoppingCartId}/book/{bookId}")
    @ApiOperation(value = "Adicionar livro a um carrinho de compras")
	public ResponseEntity<ShoppingCart> addBook(
			HttpServletRequest request,
            @ApiParam(value = "ID do carrinho de compras ao qual o livro será adicionado.", required = true)
			@PathVariable String shoppingCartId, 
            @ApiParam(value = "ID do livro a ser adicionado.", required = true)
            @PathVariable String bookId) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		ShoppingCart shoppingCart = shoppingCartService.addBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
	
	@DeleteMapping("/{shoppingCartId}/book/{bookId}")
    @ApiOperation(value = "Remover livro de um carrinho de compras")
	public ResponseEntity<ShoppingCart> removeBook(
			HttpServletRequest request,
            @ApiParam(value = "ID do carrinho de compras do qual o livro será removido.", required = true)
			@PathVariable String shoppingCartId, 
            @ApiParam(value = "ID do livro a ser removido.", required = true)
            @PathVariable String bookId) throws NotAuthorizedException, IOException, URISyntaxException {
		Authentication.authenticateUser(request);
		ShoppingCart shoppingCart = shoppingCartService.removeBook(shoppingCartId, bookId);
		return ResponseEntity.ok().body(shoppingCart);
	}
}

package com.atividade.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;
import com.atividade.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private List<Book> bookList;
	
	@PostConstruct
	private void buildMockBookList() {
		this.bookList = new ArrayList<>();		
		this.bookList.add(new Book("Book 1", "Author 1", "Ed 1", 12.90));
		this.bookList.add(new Book("Book 2", "Author 2", "Ed 2", 25.90));
		this.bookList.add(new Book("Book 3", "Author 3", "Ed 3", 39.90));
	}
	
	@Override
	public Book getById(String id) {
		return findOrFail(id);
	}
	
	@Override
	public List<Book> getList(String title, String author, String edition, Double price){
		return bookList.stream().filter(
				element -> {
					return
						element.getTitle().contains(title == null ? "" : title) &&
						element.getAuthor().contains(author == null ? "" : author) &&
						element.getEdition().contains(edition == null ? "" : edition) &&
						(price == null ? true : element.getPrice().equals(price));
				}
			).collect(Collectors.toList());
	}

	@Override
	public Book create(String title, String author, String edition, Double price) {
		return new Book(title, author, edition, price);
	}
	
	@Override
	public Book getRandom() {
		Random random = new Random(); 
		return bookList.get(random.nextInt(bookList.size())); 
	}

	@Override
	public Comment postComment(String bookId, String text, String author) {
		Book book = findOrFail(bookId);
		
		Comment comment = new Comment(book, text, author);
		return comment;
	}
	
	public Book findOrFail(String id) {
		Book book = bookList.stream().filter(element -> element.getId().equals(id)).findAny().orElse(null);
		
		if(book == null) {
			throw new NoSuchElementException(id);
		}
		
		return book;
	}
}

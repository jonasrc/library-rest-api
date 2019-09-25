package com.atividade.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;
import com.atividade.library.exception.BookNotFoundException;
import com.atividade.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private List<Book> bookList;
	
	@PostConstruct
	private void buildMockBookList() {
		this.bookList = new ArrayList<>();		
		this.bookList.add(new Book("The Demon Haunted World", "Carl Sagan", "1st", 23.94));
		this.bookList.add(new Book("A Brief History of Time", "Stephen Hawking", "2nd", 25.90));
		this.bookList.add(new Book("The God Delusion", "Richard Dawkins", "3rd", 39.92));
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
			throw new BookNotFoundException(id);
		}
		
		return book;
	}
}

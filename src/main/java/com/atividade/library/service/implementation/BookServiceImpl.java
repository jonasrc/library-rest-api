package com.atividade.library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atividade.library.domain.Book;
import com.atividade.library.domain.Comment;
import com.atividade.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private List<Book> bookList = buildMockBookList();

	@Override
	public Book postBook(String title, String author, String edition, Double price) {
		return new Book(title, author, edition, price);
	}
	
	@Override
	public Book findBook(String id) {
		return bookList.stream().filter(
				element -> element.getId().equals(id))
			.findAny()
			.orElse(null);
	}
	
	@Override
	public Book findBookOrFail(String id) {
		Book book = findBook(id);
		
		if(book == null) {
			throw new NoSuchElementException("Book with id \"" + id + "\" not found.");
		}
		
		return book;
	}
	
	@Override
	public Book getRandomBook() {
		Random random = new Random(); 
		return bookList.get(random.nextInt(bookList.size())); 
	}

	@Override
	public Comment postComment(String bookId, String text, String author) {
		Book book = findBookOrFail(bookId);
		
		Comment coment = new Comment(book, text, author);
		return coment;
	}
	
	@Override
	public List<Book> searchBooks(String title, String author, String edition, Double price){
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
	
	private List<Book> buildMockBookList() {
		List<Book> list = new ArrayList<>();		
		list.add(new Book("Book 1", "Author 1", "Ed 1", 12.90));
		list.add(new Book("Book 2", "Author 2", "Ed 2", 25.90));
		list.add(new Book("Book 3", "Author 3", "Ed 3", 39.90));
		
		return list;
	}
}

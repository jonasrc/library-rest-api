package com.atividade.library;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {

	@GetMapping("/its-alive")
	public String itsAlive() {
		return "It's alive!";
	}
	
	@PostMapping("/")
	public Book postBook() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Book.class);
		   
	    Book book = context.getBean(Book.class);
	    book.setTitle("The Demon Haunted World");
	    book.setAuthor("Carl Sagan");
	    book.setEdition("3rd");
	    
	    ((ConfigurableApplicationContext)context).close();
	    
	    return book;
	}

	@PostMapping("/{idBook}/comment")
	public Comment postComment() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Comment.class);
		   
	    Comment comment = context.getBean(Comment.class);
	    comment.setText("Amazing book!");
	    
	    ((ConfigurableApplicationContext)context).close();
	    
	    return comment;
	}
}

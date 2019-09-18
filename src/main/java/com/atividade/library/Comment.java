package com.atividade.library;

import org.springframework.stereotype.Component;

@Component
public class Comment implements CommentInterface {
	
	private Integer bookId;
	
	private String text;
	
	public Comment(Integer bookId, String text) {
		this.bookId = bookId;
		this.text = text;
	}

	@Override
	public Integer getBookId() {
		return bookId;
	}

	@Override
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}
}

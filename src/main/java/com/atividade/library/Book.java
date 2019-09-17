package com.atividade.library;

import org.springframework.stereotype.Component;

@Component
public class Book implements BookInterface {
	private String title = "The Demon Haunted World";
	private String author = "Carl Sagan";
	private String ed = "3rd";
}

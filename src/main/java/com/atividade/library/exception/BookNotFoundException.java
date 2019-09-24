package com.atividade.library.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String id) {
        super("Book with ID \"" + id + "\" not found.");
    }
}

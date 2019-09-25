package com.atividade.library.exception;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException(String id) {
        super("Book with ID \"" + id + "\" not found.");
    }
}

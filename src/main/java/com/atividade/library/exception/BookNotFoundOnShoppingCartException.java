package com.atividade.library.exception;

import java.util.NoSuchElementException;

public class BookNotFoundOnShoppingCartException extends NoSuchElementException {
    public BookNotFoundOnShoppingCartException(String id) {
        super("Book with ID \"" + id + "\" not found on shopping cart.");
    }
}

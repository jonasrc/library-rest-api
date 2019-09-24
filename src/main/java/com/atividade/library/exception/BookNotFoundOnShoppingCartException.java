package com.atividade.library.exception;

public class BookNotFoundOnShoppingCartException extends Exception {
    public BookNotFoundOnShoppingCartException(String id) {
        super("Book with ID \"" + id + "\" not found on shopping cart.");
    }
}

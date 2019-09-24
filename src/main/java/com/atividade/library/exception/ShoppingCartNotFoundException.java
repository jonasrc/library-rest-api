package com.atividade.library.exception;

public class ShoppingCartNotFoundException extends Exception {
    public ShoppingCartNotFoundException(String id) {
        super("Shopping cart with ID \"" + id + "\" not found.");
    }
}

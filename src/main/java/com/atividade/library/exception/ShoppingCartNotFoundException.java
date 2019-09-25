package com.atividade.library.exception;

import java.util.NoSuchElementException;

public class ShoppingCartNotFoundException extends NoSuchElementException {
    public ShoppingCartNotFoundException(String id) {
        super("Shopping cart with ID \"" + id + "\" not found.");
    }
}

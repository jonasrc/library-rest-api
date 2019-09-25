package com.atividade.library.exception;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends NoSuchElementException {
    public OrderNotFoundException(String id) {
        super("Order with ID \"" + id + "\" not found.");
    }
}

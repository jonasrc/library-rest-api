package com.atividade.library.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String id) {
        super("Order with ID \"" + id + "\" not found.");
    }
}

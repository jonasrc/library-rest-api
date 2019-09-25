package com.atividade.library.exception;

import javax.validation.ConstraintViolationException;

public class ShoppingCartIsEmptyException extends ConstraintViolationException {
    public ShoppingCartIsEmptyException(String id) {
        super("Shopping cart with ID \"" + id + "\" is empty.", null);
    }
}

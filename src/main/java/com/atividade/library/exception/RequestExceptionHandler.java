package com.atividade.library.exception;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> sqlError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getClass());
    }

    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> notFoundError(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ BookNotFoundException.class })
    public ResponseEntity<Object> bookNotFoundError(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
//
//    @ExceptionHandler({ EmptyResultDataAccessException.class })
//    public ResponseEntity<Object> deleteError(Exception ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//
//    @ExceptionHandler({ TransactionSystemException.class })
//    public ResponseEntity<Object> updateInexistentError(Exception ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }
//
//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> updateConstraintError(Exception ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }
}
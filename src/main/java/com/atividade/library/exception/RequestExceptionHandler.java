package com.atividade.library.exception;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.apache.http.client.ClientProtocolException;
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

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> updateConstraintError(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({ IOException.class })
    public ResponseEntity<Object> inputOutputError(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({ URISyntaxException.class })
    public ResponseEntity<Object> syntaxError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler({ ClientProtocolException.class })
    public ResponseEntity<Object> clientProtocolError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler({ NotAuthorizedException.class })
    public ResponseEntity<Object> notAuthorizedError(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
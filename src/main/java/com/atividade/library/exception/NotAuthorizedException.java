package com.atividade.library.exception;

import org.apache.http.auth.AuthenticationException;

public class NotAuthorizedException extends AuthenticationException {
    public NotAuthorizedException() {
        super("You are not authorized to access this resource.");
    }
}

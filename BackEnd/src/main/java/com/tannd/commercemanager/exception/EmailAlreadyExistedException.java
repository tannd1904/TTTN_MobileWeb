package com.tannd.commercemanager.exception;

public class EmailAlreadyExistedException extends RuntimeException{
    public EmailAlreadyExistedException(String message) {
        super(message);
    }
}

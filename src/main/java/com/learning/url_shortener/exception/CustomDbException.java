package com.learning.url_shortener.exception;

public class CustomDbException extends RuntimeException {

    public CustomDbException(String message) {
        super(message);
    }

}

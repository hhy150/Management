package com.example.management.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message){
        super(message);
    }
}

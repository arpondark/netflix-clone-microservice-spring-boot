package com.arpon007.netflixclone.auth.exception;

public class InvalidCredentialsExpection extends RuntimeException {
    public InvalidCredentialsExpection(String message) {
        super(message);
    }
}

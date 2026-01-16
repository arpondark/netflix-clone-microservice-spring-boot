package com.arpon007.netflixclone.auth.exception;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException(String message) {
        super(message);
    }
}

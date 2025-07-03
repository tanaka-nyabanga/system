package com.user.exception;

public class PasswordHasNoSpecCharException extends RuntimeException {
    public PasswordHasNoSpecCharException(String message) {
        super(message);
    }
}

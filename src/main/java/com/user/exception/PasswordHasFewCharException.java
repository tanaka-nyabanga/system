package com.user.exception;

public class PasswordHasFewCharException extends RuntimeException {
    public PasswordHasFewCharException(String message) {
        super(message);
    }
}

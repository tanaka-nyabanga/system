package com.user.exception;

public class PasswordHasnoNumbersException extends RuntimeException {
    public PasswordHasnoNumbersException(String message) {
        super(message);
    }
}

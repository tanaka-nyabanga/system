package com.user.exception;

public class DuplicateExpception extends RuntimeException {
    public DuplicateExpception(String message) {
        super(message);
    }
}

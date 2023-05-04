package com.kazakov.eventkeeper.mainservice.exceptions;

public class CategoryDeleteDeniedException extends RuntimeException {
    public CategoryDeleteDeniedException(String message) {
        super(message);
    }
}

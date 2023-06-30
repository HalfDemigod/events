package com.kazakov.events.mainservice.exceptions;

public class CategoryDeleteDeniedException extends RuntimeException {
    public CategoryDeleteDeniedException(String message) {
        super(message);
    }
}

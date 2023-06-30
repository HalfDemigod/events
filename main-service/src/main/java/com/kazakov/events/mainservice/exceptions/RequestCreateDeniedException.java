package com.kazakov.events.mainservice.exceptions;

public class RequestCreateDeniedException extends RuntimeException {
    public RequestCreateDeniedException(String message) {
        super(message);
    }
}

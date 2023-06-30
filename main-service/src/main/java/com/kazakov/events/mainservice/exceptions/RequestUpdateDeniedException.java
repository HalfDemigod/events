package com.kazakov.events.mainservice.exceptions;

public class RequestUpdateDeniedException extends RuntimeException {
    public RequestUpdateDeniedException(String message) {
        super(message);
    }
}

package com.kazakov.eventkeeper.mainservice.exceptions;

public class RequestUpdateDeniedException extends RuntimeException {
    public RequestUpdateDeniedException(String message) {
        super(message);
    }
}

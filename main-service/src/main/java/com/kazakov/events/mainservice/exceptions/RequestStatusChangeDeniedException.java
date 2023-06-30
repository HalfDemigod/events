package com.kazakov.events.mainservice.exceptions;

public class RequestStatusChangeDeniedException extends RuntimeException {
    public RequestStatusChangeDeniedException(String message) {
        super(message);
    }
}

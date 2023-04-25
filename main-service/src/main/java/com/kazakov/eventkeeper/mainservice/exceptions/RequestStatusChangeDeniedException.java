package com.kazakov.eventkeeper.mainservice.exceptions;

public class RequestStatusChangeDeniedException extends RuntimeException {
    public RequestStatusChangeDeniedException(String message) {
        super(message);
    }
}

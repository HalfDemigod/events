package com.kazakov.eventkeeper.mainservice.exceptions;

public class EventCreateDeniedException extends RuntimeException {
    public EventCreateDeniedException(String message) {
        super(message);
    }
}

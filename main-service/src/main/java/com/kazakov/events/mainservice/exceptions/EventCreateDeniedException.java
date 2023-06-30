package com.kazakov.events.mainservice.exceptions;

public class EventCreateDeniedException extends RuntimeException {
    public EventCreateDeniedException(String message) {
        super(message);
    }
}

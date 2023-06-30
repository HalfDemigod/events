package com.kazakov.events.mainservice.exceptions;

public class EventUpdateDeniedException extends RuntimeException {
    public EventUpdateDeniedException(String message) {
        super(message);
    }
}

package com.kazakov.eventkeeper.mainservice.exceptions;

public class EventUpdateDeniedException extends RuntimeException {
    public EventUpdateDeniedException(String message) {
        super(message);
    }
}

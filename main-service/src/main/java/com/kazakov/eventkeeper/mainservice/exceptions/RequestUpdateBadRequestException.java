package com.kazakov.eventkeeper.mainservice.exceptions;

public class RequestUpdateBadRequestException extends RuntimeException {
    public RequestUpdateBadRequestException(String message) {
        super(message);
    }
}

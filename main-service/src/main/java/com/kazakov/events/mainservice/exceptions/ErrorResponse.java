package com.kazakov.events.mainservice.exceptions;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@Jacksonized
public class ErrorResponse {
    List<String> errors;
    String message;
    String reason;
    String status;
    LocalDateTime timestamp;
}

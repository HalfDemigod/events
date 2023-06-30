package com.kazakov.events.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.events.mainservice.model.RequestStatus;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class RequestDto {
    LocalDateTime created;
    Long event;
    Long id;
    Long requester;
    RequestStatus status;
}

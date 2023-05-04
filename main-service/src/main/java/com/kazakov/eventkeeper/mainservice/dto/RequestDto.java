package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.eventkeeper.mainservice.model.RequestStatus;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class RequestDto {
    LocalDateTime created;
    Integer event;
    Integer id;
    Integer requester;
    RequestStatus status;
}

package com.kazakov.events.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.events.mainservice.model.RequestStatus;

import java.util.List;

@Value
@Builder
@Jacksonized
public class RequestUpdateDto {
    List<Long> requestIds;
    RequestStatus status;
}

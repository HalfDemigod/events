package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.eventkeeper.mainservice.model.Location;
import com.kazakov.eventkeeper.mainservice.validation.DateInFuture;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventUpdatetDto {

    @Size(min = 20, max = 2000)
    String annotation;
    Integer category;
    @Size(min = 20, max = 7000)
    String description;
    @DateInFuture(2)
    LocalDateTime eventDate;
    Location location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    EventAction stateAction;
    @Size(min = 3, max = 120)
    String title;
}

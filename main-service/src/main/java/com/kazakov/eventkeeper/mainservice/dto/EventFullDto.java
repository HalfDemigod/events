package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.eventkeeper.mainservice.model.EventState;
import com.kazakov.eventkeeper.mainservice.model.Location;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class EventFullDto {
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    LocalDateTime createdOn;
    String description;
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator;
    Location location;
    Boolean paid;
    Integer participantLimit;
    LocalDateTime publishedOn;
    Boolean requestModeration;
    EventState state;
    String title;
    Long views;
}

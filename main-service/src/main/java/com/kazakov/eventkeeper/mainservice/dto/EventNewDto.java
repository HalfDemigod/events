package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import com.kazakov.eventkeeper.mainservice.model.Location;
import com.kazakov.eventkeeper.mainservice.validation.DateInFuture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventNewDto {
    @Size(min = 20, max = 2000)
    @NotBlank
    String annotation;
    @NotNull
    Long category;
    @Size(min = 20, max = 7000)
    @NotBlank
    String description;
    @DateInFuture(2)
    LocalDateTime eventDate;
    @NotNull
    Location location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    @Size(min = 3, max = 120)
    @NotBlank
    String title;
}

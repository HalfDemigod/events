package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import jakarta.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class CategoryNewDto {
    @NotBlank
    String name;
}

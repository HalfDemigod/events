package com.kazakov.events.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
@Jacksonized
public class CompilationNewDto {
    @NotNull
    List<Long> events;
    Boolean pinned;
    @NotBlank
    String title;
}

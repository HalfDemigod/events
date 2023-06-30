package com.kazakov.events.mainservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CommentNewDto {
    @NotNull
    Long event;
    @Size(min = 1, max = 2000)
    @NotBlank
    String text;
}

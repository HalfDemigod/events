package com.kazakov.eventkeeper.mainservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CommentUpdateDto {
    @Size(min = 1, max = 2000)
    @NotBlank
    String text;
}

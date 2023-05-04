package com.kazakov.eventkeeper.mainservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserShortDto {
    String email;
    @NotBlank
    String name;
    @NotBlank
    String password;
    Boolean admin;
}

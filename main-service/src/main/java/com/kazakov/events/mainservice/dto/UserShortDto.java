package com.kazakov.events.mainservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserShortDto {
    @Email
    String email;
    @NotBlank
    String name;
    @NotBlank
    String password;
    boolean admin;
}

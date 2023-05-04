package com.kazakov.eventkeeper.mainservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserShortDto {
    String email;
    String name;
}

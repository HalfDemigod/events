package com.kazakov.events.statsdto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class StatsDto {
    String app;
    String uri;
    Long hits;
}

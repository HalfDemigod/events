package com.kazakov.events.statsserver.services;

import com.kazakov.events.statsdto.EndpointHitDto;
import com.kazakov.events.statsdto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

    EndpointHitDto createHit(EndpointHitDto endpointHitDto);
}

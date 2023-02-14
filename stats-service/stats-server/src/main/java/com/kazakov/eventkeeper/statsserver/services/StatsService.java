package com.kazakov.eventkeeper.statsserver.services;

import com.kazakov.eventkeeper.statsdto.EndpointHitDto;
import com.kazakov.eventkeeper.statsdto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

    EndpointHitDto createHit(EndpointHitDto endpointHitDto);
}

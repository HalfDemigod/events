package com.kazakov.eventkeeper.statsserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kazakov.eventkeeper.statsdto.EndpointHitDto;
import com.kazakov.eventkeeper.statsdto.*;
import com.kazakov.eventkeeper.statsserver.services.StatsService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("stats")
    public ResponseEntity<List<StatsDto>> getStats(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                   @RequestParam(required = false) List<String> uris,
                                                   @RequestParam(required = false, defaultValue = "false") Boolean unique) {
        return ResponseEntity.ok(statsService.getStats(start, end, uris, unique));
    }

    @PostMapping("hit")
    public ResponseEntity<EndpointHitDto> createHit(@RequestBody EndpointHitDto endpointHitDto) {
        return new ResponseEntity<>(statsService.createHit(endpointHitDto), HttpStatus.CREATED);
    }
}

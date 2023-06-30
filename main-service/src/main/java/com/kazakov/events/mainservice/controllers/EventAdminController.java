package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.security.Roles;
import com.kazakov.events.mainservice.dto.EventFullDto;
import com.kazakov.events.mainservice.dto.EventUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.kazakov.events.mainservice.model.EventState;
import com.kazakov.events.mainservice.services.EventService;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Secured(Roles.ROLE_ADMIN)
public class EventAdminController {
    private final EventService eventService;

    @GetMapping("")
    public ResponseEntity<List<EventFullDto>> findAllEvents(@RequestParam(required = false) List<Long> users,
                                                            @RequestParam(required = false) List<EventState> states,
                                                            @RequestParam(required = false) List<Long> categories,
                                                            @RequestParam(required = false)
                                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                LocalDateTime rangeStart,
                                                            @RequestParam(required = false)
                                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                LocalDateTime rangeEnd,
                                                            @RequestParam(required = false) Integer from,
                                                            @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(eventService.findAllEvents(users, states, categories, rangeStart, rangeEnd, from, size));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventFullDto> updateEvent(@Valid @RequestBody EventUpdateDto eventUpdateDto,
                                                          @PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.updateEvent(eventUpdateDto, null, eventId));
    }

}

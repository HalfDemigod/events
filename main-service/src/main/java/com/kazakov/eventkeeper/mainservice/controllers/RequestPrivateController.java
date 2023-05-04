package com.kazakov.eventkeeper.mainservice.controllers;

import com.kazakov.eventkeeper.mainservice.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.kazakov.eventkeeper.mainservice.dto.RequestDto;
import com.kazakov.eventkeeper.mainservice.services.RequestService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Secured(Roles.ROLE_USER)
public class RequestPrivateController {
    private final RequestService requestService;

    @GetMapping
    public ResponseEntity<List<RequestDto>> findAllUserRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.findAllUserRequests(userId));
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@PathVariable Long userId,
                                                    @RequestParam Long eventId) {
        return new ResponseEntity<>(requestService.createRequest(userId, eventId), HttpStatus.CREATED);
    }

    @PatchMapping("/{requestId}/cancel")
    public ResponseEntity<RequestDto> cancelRequest(@PathVariable Long userId,
                                                    @PathVariable Long requestId) {
        return ResponseEntity.ok(requestService.cancelRequest(userId, requestId));
    }
}

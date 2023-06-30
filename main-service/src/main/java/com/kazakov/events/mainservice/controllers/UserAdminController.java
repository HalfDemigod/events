package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.security.Roles;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.kazakov.events.mainservice.dto.UserDto;
import com.kazakov.events.mainservice.dto.UserShortDto;
import com.kazakov.events.mainservice.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Secured(Roles.ROLE_ADMIN)
public class UserAdminController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(@RequestParam(required = false) List<Long> ids,
                                                      @RequestParam(required = false) Integer from,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(userService.findAllUsers(ids, from, size));
    }

    @PostMapping
    public  ResponseEntity<UserDto> createUser(@Valid @RequestBody UserShortDto userShortDto) {
        return new ResponseEntity<>(userService.createUser(userShortDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
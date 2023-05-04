package com.kazakov.eventkeeper.mainservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kazakov.eventkeeper.mainservice.dto.UserDto;
import com.kazakov.eventkeeper.mainservice.dto.UserShortDto;
import com.kazakov.eventkeeper.mainservice.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(@RequestParam(required = false) List<Long> ids,
                                                      @RequestParam(required = false) Integer from,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(userService.findAllUsers(ids, from, size));
    }

    @PostMapping
    public  ResponseEntity<UserDto> createUser(@RequestBody UserShortDto userShortDto) {
        return new ResponseEntity<>(userService.createUser(userShortDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

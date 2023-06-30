package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.dto.CommentDto;
import com.kazakov.events.mainservice.dto.CommentNewDto;
import com.kazakov.events.mainservice.dto.CommentUpdateDto;
import com.kazakov.events.mainservice.security.Roles;
import com.kazakov.events.mainservice.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
@Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
public class CommentPrivateController {
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<CommentDto>> findAllComments(@PathVariable Long userId,
                                                            @RequestParam(required = false) Long eventId,
                                                            @RequestParam(required = false) Integer from,
                                                            @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok((commentService.findAllComments(userId, eventId, from, size)));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> findComment(@PathVariable Long userId, @PathVariable Long commentId) {
        return ResponseEntity.ok((commentService.findComment(userId, commentId)));
    }

    @PostMapping("")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentNewDto commentNewDto,
                                                    @PathVariable Long userId) {
        return new ResponseEntity<>(commentService.createComment(commentNewDto, userId), HttpStatus.CREATED);
    }

    @PatchMapping("{commentId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto,
                                                    @PathVariable Long userId,
                                                    @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.updateComment(commentUpdateDto, userId, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(userId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

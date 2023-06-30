package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.dto.CommentDto;
import com.kazakov.events.mainservice.dto.CommentUpdateDto;
import com.kazakov.events.mainservice.security.Roles;
import com.kazakov.events.mainservice.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Secured(Roles.ROLE_ADMIN)
public class CommentAdminController {
    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto,
                                                    @PathVariable Long commentId
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentUpdateDto, commentId));
    }
}
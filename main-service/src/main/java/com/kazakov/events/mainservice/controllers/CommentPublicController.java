package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.dto.CommentDto;
import com.kazakov.events.mainservice.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentPublicController {
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<CommentDto>> findAllComments(@RequestParam(required = false) Long eventId,
                                                            @RequestParam(required = false) Integer from,
                                                            @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok((commentService.findAllComments(eventId, from, size)));
    }

    @GetMapping("{commentId}")
    public ResponseEntity<CommentDto> findComment(@PathVariable Long commentId) {
        return ResponseEntity.ok((commentService.findComment(commentId)));
    }
}

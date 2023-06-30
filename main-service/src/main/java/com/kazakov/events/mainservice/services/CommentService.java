package com.kazakov.events.mainservice.services;

import com.kazakov.events.mainservice.dto.CommentDto;
import com.kazakov.events.mainservice.dto.CommentNewDto;
import com.kazakov.events.mainservice.dto.CommentUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentDto> findAllComments(Long eventId, Integer from, Integer size);

    List<CommentDto> findAllComments(Long userId, Long eventId, Integer from, Integer size);

    CommentDto findComment(Long commentId);

    CommentDto findComment(Long userId, Long commentId);

    CommentDto createComment(CommentNewDto commentNewDto, Long userId);

    CommentDto updateComment(CommentUpdateDto commentUpdateDto, Long userId, Long commentId);

    CommentDto updateComment(CommentUpdateDto commentUpdateDto, Long commentId);

    void deleteComment(Long userId, Long commentId);

    void deleteComment(Long commentId);
}

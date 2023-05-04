package com.kazakov.eventkeeper.mainservice.services.impl;

import com.kazakov.eventkeeper.mainservice.dao.CommentRepository;
import com.kazakov.eventkeeper.mainservice.dao.EventRepository;
import com.kazakov.eventkeeper.mainservice.dao.UserRepository;
import com.kazakov.eventkeeper.mainservice.dto.CommentDto;
import com.kazakov.eventkeeper.mainservice.dto.CommentNewDto;
import com.kazakov.eventkeeper.mainservice.dto.CommentUpdateDto;
import com.kazakov.eventkeeper.mainservice.dto.mappers.CommentsMapper;
import com.kazakov.eventkeeper.mainservice.exceptions.CommentNotFoundException;
import com.kazakov.eventkeeper.mainservice.exceptions.EventNotFoundException;
import com.kazakov.eventkeeper.mainservice.exceptions.UserNotFoundException;
import com.kazakov.eventkeeper.mainservice.model.Comment;
import com.kazakov.eventkeeper.mainservice.model.Event;
import com.kazakov.eventkeeper.mainservice.model.EventState;
import com.kazakov.eventkeeper.mainservice.model.User;
import com.kazakov.eventkeeper.mainservice.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id=%d was not found", id)));
    }

    private Event findEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        String.format("Event with id=%d was not found", id)));
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new EventNotFoundException(String.format("Event with id=%d was not found", id));
        }
        return event;
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(
                        String.format("Comment with id=%d was not found", commentId)));
    }

    @Override
    public List<CommentDto> findAllComments(Long eventId, Integer from, Integer size) {
        Pageable pageable;
        if (size == null) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "created"));
        } else {
            pageable = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "created"));
        }

        if (eventId != null) {
            Event event = findEventById(eventId);
            return commentRepository.findAllByEvent(event, pageable)
                    .stream()
                    .map(commentsMapper::commentToCommentDto)
                    .collect(Collectors.toList());
        } else {
            return commentRepository.findAll(pageable)
                    .stream()
                    .map(commentsMapper::commentToCommentDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<CommentDto> findAllComments(Long userId, Long eventId, Integer from, Integer size) {
        User user = findUserById(userId);

        Pageable pageable;
        if (size == null) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "created"));
        } else {
            pageable = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "created"));
        }

        if (eventId != null) {
            Event event = findEventById(eventId);
            return commentRepository.findAllByEventAndAuthor(event, user, pageable)
                    .stream()
                    .map(commentsMapper::commentToCommentDto)
                    .collect(Collectors.toList());
        } else {
            return commentRepository.findAllByAuthor(user, pageable)
                    .stream()
                    .map(commentsMapper::commentToCommentDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public CommentDto findComment(Long commentId) {
        return commentsMapper.commentToCommentDto(findCommentById(commentId));
    }

    @Override
    public CommentDto findComment(Long userId, Long commentId) {
        User user = findUserById(userId);
        Comment comment = findCommentById(commentId);
        if (!comment.getAuthor().equals(user)) {
            throw new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId));
        }
        return commentsMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto createComment(CommentNewDto commentNewDto, Long userId) {
        User user = findUserById(userId);
        Event event = findEventById(commentNewDto.getEvent());

        Comment comment = commentsMapper.commentNewDtoToComment(commentNewDto);
        comment.setAuthor(user);
        comment.setEvent(event);
        comment.setCreated(LocalDateTime.now());

        return commentsMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(CommentUpdateDto commentUpdateDto, Long userId, Long commentId) {
        User user = findUserById(userId);
        Comment comment = findCommentById(commentId);

        if (!comment.getAuthor().equals(user)) {
            throw  new CommentNotFoundException(
                    String.format("Comment with id=%d was not found", commentId));
        }

        comment.setText(commentUpdateDto.getText());

        return commentsMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(CommentUpdateDto commentUpdateDto, Long commentId) {
        Comment comment = findCommentById(commentId);
        comment.setText(commentUpdateDto.getText());
        return commentsMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        User user = findUserById(userId);
        Comment comment = findCommentById(commentId);
        if (!comment.getAuthor().equals(user)) {
            throw new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId));
        }
        commentRepository.delete(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);
        commentRepository.delete(comment);
    }
}

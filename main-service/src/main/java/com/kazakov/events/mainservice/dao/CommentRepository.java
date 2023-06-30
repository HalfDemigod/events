package com.kazakov.events.mainservice.dao;

import com.kazakov.events.mainservice.model.Comment;
import com.kazakov.events.mainservice.model.Event;
import com.kazakov.events.mainservice.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEvent(Event event, Pageable pageable);

    List<Comment> findAllByEventAndAuthor(Event event, User user, Pageable pageable);

    List<Comment> findAllByAuthor(User user, Pageable pageable);
}

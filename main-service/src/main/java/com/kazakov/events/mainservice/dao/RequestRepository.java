package com.kazakov.events.mainservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kazakov.events.mainservice.model.Event;
import com.kazakov.events.mainservice.model.Request;
import com.kazakov.events.mainservice.model.RequestStatus;
import com.kazakov.events.mainservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByEvent(Event event);

    List<Request> findAllByEventAndStatus(Event event, RequestStatus requestStatus);

    List<Request> findAllByIdIn(List<Long> id);

    Optional<Request> findAllByRequesterAndEvent(User user, Event event);

    List<Request> findAllByRequester(User user);

    List<Request> findAllByEventInitiatorAndStatus(User user, RequestStatus requestStatus);

    List<Request> findAllByEventInAndStatus(List<Event> events, RequestStatus requestStatus);

}

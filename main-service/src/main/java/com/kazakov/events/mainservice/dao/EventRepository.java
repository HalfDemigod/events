package com.kazakov.events.mainservice.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.kazakov.events.mainservice.model.Event;
import com.kazakov.events.mainservice.model.User;

import java.util.List;

@Repository
public interface EventRepository extends
        JpaRepository<Event, Long>,
        EventRepositoryCustom,
        JpaSpecificationExecutor<Event> {

    List<Event> findAllByInitiator(User initiator, Pageable pageable);

    List<Event> findAllByCategoryId(Long categoryId);

}

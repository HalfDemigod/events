package com.kazakov.events.mainservice.dao;

import org.springframework.data.domain.Pageable;
import com.kazakov.events.mainservice.model.Event;
import com.kazakov.events.mainservice.model.EventState;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findAllEventsWithFilter(List<Long> users,
                                        List<EventState> states,
                                        List<Long> categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Pageable pageable);

    List<Event> findAllPublicEventsWithFilter(String text,
                                              List<Long> categories,
                                              Boolean paid,
                                              LocalDateTime rangeStart,
                                              LocalDateTime rangeEnd,
                                              Boolean onlyAvailable,
                                              String sort,
                                              Integer from,
                                              Integer size);
}

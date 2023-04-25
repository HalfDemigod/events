package com.kazakov.eventkeeper.mainservice.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.eventkeeper.mainservice.dto.EventFullDto;
import com.kazakov.eventkeeper.mainservice.dto.EventNewDto;
import com.kazakov.eventkeeper.mainservice.dto.EventShortDto;
import com.kazakov.eventkeeper.mainservice.model.Event;

@Component
@Mapper(uses = {CategoryMapper.class, UserMapper.class}, componentModel = "spring")
public interface EventMapper {

    EventShortDto eventToEventShortDto(Event event);

    EventFullDto eventToEventFullDto(Event event);

    @Mapping(target = "category", ignore = true)
    Event eventNewDtoToEvent(EventNewDto eventNewDto);
}

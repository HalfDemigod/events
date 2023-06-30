package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.EventFullDto;
import com.kazakov.events.mainservice.dto.EventNewDto;
import com.kazakov.events.mainservice.dto.EventShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.events.mainservice.model.Event;

@Component
@Mapper(uses = {CategoryMapper.class, UserMapper.class}, componentModel = "spring")
public interface EventMapper {

    EventShortDto eventToEventShortDto(Event event);

    EventFullDto eventToEventFullDto(Event event);

    @Mapping(target = "category", ignore = true)
    Event eventNewDtoToEvent(EventNewDto eventNewDto);
}

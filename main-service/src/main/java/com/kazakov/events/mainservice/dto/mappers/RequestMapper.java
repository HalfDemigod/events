package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.RequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.events.mainservice.model.Request;

@Component
@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(source = "event.id", target = "event")
    @Mapping(source = "requester.id", target = "requester")
    RequestDto requestToRequestDto(Request request);
}

package com.kazakov.eventkeeper.mainservice.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.eventkeeper.mainservice.dto.RequestDto;
import com.kazakov.eventkeeper.mainservice.model.Request;

@Component
@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(source = "event.id", target = "event")
    @Mapping(source = "requester.id", target = "requester")
    RequestDto requestToRequestDto(Request request);
}

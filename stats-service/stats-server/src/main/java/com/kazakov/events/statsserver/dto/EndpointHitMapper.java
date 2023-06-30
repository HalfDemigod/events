package com.kazakov.events.statsserver.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.kazakov.events.statsdto.EndpointHitDto;
import com.kazakov.events.statsserver.model.EndpointHit;

@Mapper(uses = {EndpointHit.class})
public interface EndpointHitMapper {
    EndpointHitMapper INSTANCE = Mappers.getMapper(EndpointHitMapper.class);

    EndpointHitDto endpointHitToEndpointHitDto(EndpointHit endpointHit);

    EndpointHit endpointHitDtoToEndpointHit(EndpointHitDto endpointHitDto);
}

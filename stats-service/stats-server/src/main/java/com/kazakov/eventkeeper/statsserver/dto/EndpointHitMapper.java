package com.kazakov.eventkeeper.statsserver.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.kazakov.eventkeeper.statsdto.EndpointHitDto;
import com.kazakov.eventkeeper.statsserver.model.EndpointHit;

@Mapper(uses = {EndpointHit.class})
public interface EndpointHitMapper {
    EndpointHitMapper INSTANCE = Mappers.getMapper(EndpointHitMapper.class);

    EndpointHitDto endpointHitToEndpointHitDto(EndpointHit endpointHit);

    EndpointHit endpointHitDtoToEndpointHit(EndpointHitDto endpointHitDto);
}

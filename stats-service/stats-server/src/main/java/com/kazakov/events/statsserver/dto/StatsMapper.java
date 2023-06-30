package com.kazakov.events.statsserver.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.kazakov.events.statsdto.StatsDto;
import com.kazakov.events.statsserver.model.Stats;

@Mapper(uses = {Stats.class})
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    StatsDto statsToStatsDto(Stats stats);
}

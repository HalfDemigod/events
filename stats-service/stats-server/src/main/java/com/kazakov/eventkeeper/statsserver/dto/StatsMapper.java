package com.kazakov.eventkeeper.statsserver.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.kazakov.eventkeeper.statsdto.StatsDto;
import com.kazakov.eventkeeper.statsserver.model.Stats;

@Mapper(uses = {Stats.class})
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    StatsDto statsToStatsDto(Stats stats);
}

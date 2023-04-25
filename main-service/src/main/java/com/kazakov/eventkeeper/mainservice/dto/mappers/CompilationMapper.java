package com.kazakov.eventkeeper.mainservice.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.eventkeeper.mainservice.dto.CompilationDto;
import com.kazakov.eventkeeper.mainservice.dto.CompilationNewDto;
import com.kazakov.eventkeeper.mainservice.model.Compilation;

@Component
@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface CompilationMapper {

    @Mapping(source = "eventsCompilations", target = "events")
    CompilationDto compilationToCompilationDto(Compilation compilation);

    Compilation compilationNewDtoToCompilation(CompilationNewDto compilationNewDto);
}


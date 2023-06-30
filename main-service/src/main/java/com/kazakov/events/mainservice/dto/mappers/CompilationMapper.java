package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.CompilationDto;
import com.kazakov.events.mainservice.dto.CompilationNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import com.kazakov.events.mainservice.model.Compilation;

@Component
@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface CompilationMapper {

    @Mapping(source = "eventsCompilations", target = "events")
    CompilationDto compilationToCompilationDto(Compilation compilation);

    Compilation compilationNewDtoToCompilation(CompilationNewDto compilationNewDto);
}


package com.kazakov.events.mainservice.services;

import com.kazakov.events.mainservice.dto.CompilationDto;
import com.kazakov.events.mainservice.dto.CompilationNewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompilationService {
    List<CompilationDto> findAllCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto findCompilation(Long compId);

    CompilationDto createCompilation(CompilationNewDto compilationNewDto);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(CompilationNewDto compilationNewDto, Long compId);
}

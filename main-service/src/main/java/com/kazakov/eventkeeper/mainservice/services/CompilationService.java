package com.kazakov.eventkeeper.mainservice.services;

import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dto.CompilationDto;
import com.kazakov.eventkeeper.mainservice.dto.CompilationNewDto;

import java.util.List;

@Service
public interface CompilationService {
    List<CompilationDto> findAllCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto findCompilation(Long compId);

    CompilationDto createCompilation(CompilationNewDto compilationNewDto);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(CompilationNewDto compilationNewDto, Long compId);
}

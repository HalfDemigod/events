package com.kazakov.events.mainservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kazakov.events.mainservice.dto.CompilationDto;
import com.kazakov.events.mainservice.services.CompilationService;

import java.util.List;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationPublicController {
    private final CompilationService compilationService;

    @GetMapping
    public ResponseEntity<List<CompilationDto>> findAllCompilations(@RequestParam(required = false) Boolean pinned,
                                                                    @RequestParam(required = false) Integer from,
                                                                    @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(compilationService.findAllCompilations(pinned, from, size));
    }

    @GetMapping("/{compId}")
    public ResponseEntity<CompilationDto> findCompilationById(@PathVariable Long compId) {
        return ResponseEntity.ok(compilationService.findCompilation(compId));
    }
}

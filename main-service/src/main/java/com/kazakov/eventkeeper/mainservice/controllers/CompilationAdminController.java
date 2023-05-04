package com.kazakov.eventkeeper.mainservice.controllers;

import com.kazakov.eventkeeper.mainservice.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.kazakov.eventkeeper.mainservice.dto.CompilationDto;
import com.kazakov.eventkeeper.mainservice.dto.CompilationNewDto;
import com.kazakov.eventkeeper.mainservice.services.CompilationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Secured(Roles.ROLE_ADMIN)
public class CompilationAdminController {
    private final CompilationService compilationService;

    @PostMapping
    public ResponseEntity<CompilationDto> createCompilation(@Valid @RequestBody CompilationNewDto compilationNewDto) {
        return new ResponseEntity<>(compilationService.createCompilation(compilationNewDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable Long compId) {
        compilationService.deleteCompilation(compId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> updateCompilation(@RequestBody CompilationNewDto compilationNewDto,
                                                            @PathVariable Long compId) {
        return ResponseEntity.ok(compilationService.updateCompilation(compilationNewDto, compId));
    }

}

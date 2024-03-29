package com.kazakov.eventkeeper.mainservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kazakov.eventkeeper.mainservice.dto.CategoryDto;
import com.kazakov.eventkeeper.mainservice.dto.CategoryNewDto;
import com.kazakov.eventkeeper.mainservice.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    public  ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryNewDto categoryNewDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryNewDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryNewDto categoryNewDto,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryNewDto, id));
    }
}

package com.kazakov.events.mainservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kazakov.events.mainservice.dto.CategoryDto;
import com.kazakov.events.mainservice.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryPublicController {
    private final CategoryService categoryService;

    @GetMapping
    public  ResponseEntity<List<CategoryDto>> findAllCategories(@RequestParam(required = false) Integer from,
                                                               @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(categoryService.findAllCategories(from, size));
    }

    @GetMapping("/{catId}")
    public  ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long catId) {
        return ResponseEntity.ok(categoryService.findCategoryById(catId));
    }
}

package com.kazakov.events.mainservice.services;

import com.kazakov.events.mainservice.dto.CategoryDto;
import com.kazakov.events.mainservice.dto.CategoryNewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryNewDto categoryNewDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(CategoryNewDto categoryNewDto, Long id);

    List<CategoryDto> findAllCategories(Integer from, Integer size);

    CategoryDto findCategoryById(Long catId);
}

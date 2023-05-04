package com.kazakov.eventkeeper.mainservice.services;

import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dto.CategoryDto;
import com.kazakov.eventkeeper.mainservice.dto.CategoryNewDto;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryNewDto categoryNewDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(CategoryNewDto categoryNewDto, Long id);

    List<CategoryDto> findAllCategories(Integer from, Integer size);

    CategoryDto findCategoryById(Long catId);
}

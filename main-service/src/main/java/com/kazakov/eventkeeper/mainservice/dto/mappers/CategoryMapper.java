package com.kazakov.eventkeeper.mainservice.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.kazakov.eventkeeper.mainservice.dto.CategoryDto;
import com.kazakov.eventkeeper.mainservice.dto.CategoryNewDto;
import com.kazakov.eventkeeper.mainservice.model.Category;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {

    CategoryDto  categoryToCategoryDto(Category category);

    Category categoryShortDtoToCategory(CategoryNewDto categoryNewDto);
}

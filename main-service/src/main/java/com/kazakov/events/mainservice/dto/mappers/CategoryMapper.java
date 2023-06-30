package com.kazakov.events.mainservice.dto.mappers;

import com.kazakov.events.mainservice.dto.CategoryDto;
import com.kazakov.events.mainservice.dto.CategoryNewDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.kazakov.events.mainservice.model.Category;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryShortDtoToCategory(CategoryNewDto categoryNewDto);
}

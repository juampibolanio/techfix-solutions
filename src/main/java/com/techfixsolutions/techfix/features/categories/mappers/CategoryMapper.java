package com.techfixsolutions.techfix.features.categories.mappers;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryResponseDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryUpdateDto;
import com.techfixsolutions.techfix.features.categories.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getUuid(),
                category.getName(),
                category.getDescription()
        );
    }

    public void updateEntity(Category category, CategoryUpdateDto dto) {
        if (dto.name() != null && !dto.name().isBlank()) {
            category.setName(dto.name());
        }

        if (dto.description() != null && !dto.description().isBlank()) {
            category.setDescription(dto.description());
        }
    }
}

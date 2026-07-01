package com.techfixsolutions.techfix.features.categories;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryResponseDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryUpdateDto;
import com.techfixsolutions.techfix.features.categories.exceptions.CategoryAlreadyExistsException;
import com.techfixsolutions.techfix.features.categories.exceptions.CategoryNotFoundException;
import com.techfixsolutions.techfix.features.categories.mappers.CategoryMapper;
import com.techfixsolutions.techfix.features.categories.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map((mapper::toResponseDto))
                .toList();
    }

    public CategoryResponseDto findById(UUID uuid) {
        Category category = repository.findById(uuid)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + uuid + " not found."));

        return mapper.toResponseDto(category);
    }

    public CategoryResponseDto create(CategoryDto dto) {
        Category mappedCategory = mapper.toEntity(dto);

        Category savedCategory = repository.save(mappedCategory);

        return mapper.toResponseDto(savedCategory);
    }

    public CategoryResponseDto patch(UUID uuid, CategoryUpdateDto dto) {
        Category currentCategory = repository.findById(uuid)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (dto.name() != null && !dto.name().isBlank() && !currentCategory.getName().equals(dto.name())) {
            if (repository.findByName(dto.name()).isPresent())    {
                throw new CategoryAlreadyExistsException("Category with name '" + dto.name() + "' already exists");
            }
        }

        mapper.updateEntity(currentCategory, dto);

        Category updatedCategory = repository.save(currentCategory);

        return mapper.toResponseDto(updatedCategory);
    }

    public void delete(UUID uuid) {
        if (!repository.existsById(uuid)) {
            throw new CategoryNotFoundException("Category with id " + uuid + " not found");
        }
        repository.deleteById(uuid);
    }
}

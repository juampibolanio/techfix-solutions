package com.techfixsolutions.techfix.features.categories;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryUpdateDto;
import com.techfixsolutions.techfix.features.categories.exceptions.CategoryAlreadyExistsException;
import com.techfixsolutions.techfix.features.categories.exceptions.CategoryNotFoundException;
import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    public Category create(CategoryDto dto) {
        Category newCategory = Category.builder().name(dto.name()).description(dto.description()).build();
        return categoryRepository.save(newCategory);
    }

    public Category patch(UUID uuid, CategoryUpdateDto dto) {
        Category currentCategory = categoryRepository
                .findById(uuid)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (dto.name() != null && !dto.name().isBlank() && !currentCategory.getName().equals(dto.name())) {
            if (categoryRepository.findByName(dto.name()).isPresent())    {
                throw new CategoryAlreadyExistsException("Category with name '" + dto.name() + "' already exists");
            }
            currentCategory.setName(dto.name());
        }

        if (dto.description() != null && !dto.description().isBlank()) {
            currentCategory.setDescription(dto.description());
        }

        return categoryRepository.save(currentCategory);
    }

    public void delete(UUID uuid) {
        if (!userRepository.existsById(uuid)) {
            throw new CategoryNotFoundException("Category with id " + uuid + " not found");
        }
        userRepository.deleteById(uuid);
    }
}

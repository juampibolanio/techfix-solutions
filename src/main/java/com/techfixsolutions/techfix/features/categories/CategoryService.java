package com.techfixsolutions.techfix.features.categories;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findOne(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    public Category create(CategoryDto dto) {
        Category newCategory = Category.builder().name(dto.name()).description(dto.description()).build();
        return categoryRepository.save(newCategory);
    }
}

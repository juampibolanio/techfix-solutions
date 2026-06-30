package com.techfixsolutions.techfix.features.categories;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryUpdateDto;
import com.techfixsolutions.techfix.features.categories.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() { return categoryService.findAll(); }

    @GetMapping("/{id}")
    public Optional<Category> findById(@PathVariable UUID uuid) { return categoryService.findById(uuid); }

    @PostMapping
    public Category create(@Valid @RequestBody CategoryDto dto) { return categoryService.create(dto); }

    @PatchMapping("/{id}")
    public Category patch(@PathVariable UUID uuid, @Valid @RequestBody CategoryUpdateDto dto) {
        return categoryService.patch(uuid, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID uuid) { categoryService.delete(uuid); }
}

package com.techfixsolutions.techfix.features.categories;

import com.techfixsolutions.techfix.features.categories.dto.CategoryDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryResponseDto;
import com.techfixsolutions.techfix.features.categories.dto.CategoryUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@Valid @RequestBody CategoryDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<CategoryResponseDto> patch(@PathVariable UUID uuid, @Valid @RequestBody CategoryUpdateDto dto) {
        return ResponseEntity.ok(service.patch(uuid, dto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

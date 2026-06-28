package com.techfixsolutions.techfix.features.categories.dto;

import jakarta.validation.constraints.Size;

public record CategoryUpdateDto(
        @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters")
    String name,

    String description
) {
}

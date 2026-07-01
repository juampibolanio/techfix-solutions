package com.techfixsolutions.techfix.features.categories.dto;

import java.util.UUID;

public record CategoryResponseDto(
    UUID uuid,
    String name,
    String description
) {
}

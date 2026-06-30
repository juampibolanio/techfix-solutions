package com.techfixsolutions.techfix.features.users.dto;

import com.techfixsolutions.techfix.features.users.models.Roles;

import java.util.UUID;

public record UserResponseDto (
    UUID uuid,
    String fullName,
    String email,
    Roles role
) {}

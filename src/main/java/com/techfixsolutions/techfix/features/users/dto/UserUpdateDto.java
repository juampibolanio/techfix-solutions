package com.techfixsolutions.techfix.features.users.dto;

import com.techfixsolutions.techfix.features.users.models.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    String fullName,
    
    @Email(message = "Email is not valid") String email,
    Roles role) {}

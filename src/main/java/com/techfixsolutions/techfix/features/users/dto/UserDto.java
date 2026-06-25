package com.techfixsolutions.techfix.features.users.dto;

import com.techfixsolutions.techfix.features.users.models.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    String fullName,

    @Email(message = "Email is not valid") 
    String email,

    @NotBlank(message = "Role is required") 
    Roles role) {}

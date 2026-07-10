package com.techfixsolutions.techfix.features.comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CommentDto(
    @NotBlank(message = "Comment cannot be blank")
    @Size(min = 1)
    String content,

    @NotNull(message = "User ID is required")
    UUID userId,

    @NotNull(message = "Ticket ID is required")
    UUID ticketId
) { }

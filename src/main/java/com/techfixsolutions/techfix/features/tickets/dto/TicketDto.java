package com.techfixsolutions.techfix.features.tickets.dto;

import com.techfixsolutions.techfix.features.tickets.models.Priority;
import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TicketDto(
    @NotBlank(message = "Title is required")
    String title,

    String description,

    TicketStatus status,

    Priority priority,

    @NotNull(message = "Category ID is required")
    UUID categoryId,

    @NotNull(message = "Client ID is required")
    UUID clientId,

    UUID agentId
) {}

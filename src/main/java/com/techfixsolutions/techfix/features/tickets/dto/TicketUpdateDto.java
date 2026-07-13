package com.techfixsolutions.techfix.features.tickets.dto;

import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.tickets.models.Priority;
import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TicketUpdateDto(
        String title,

        String description,

        TicketStatus status,

        Priority priority,

        Category category,

        UUID clientId,

        UUID agentId
) {}

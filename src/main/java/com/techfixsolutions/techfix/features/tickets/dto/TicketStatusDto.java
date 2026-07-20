package com.techfixsolutions.techfix.features.tickets.dto;

import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import jakarta.validation.constraints.NotNull;

public record TicketStatusDto(
        @NotNull(message = "Status is required")
        TicketStatus status
) {
}

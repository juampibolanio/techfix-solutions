package com.techfixsolutions.techfix.features.tickets.dto;

import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import java.util.UUID;

public record TicketSummaryDto(
        UUID uuid,
        String title,
        TicketStatus status
) {}
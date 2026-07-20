package com.techfixsolutions.techfix.features.tickets.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignAgentTicketDto(
    @NotNull(message = "Agent id is required")
    UUID agentUuid
) { }

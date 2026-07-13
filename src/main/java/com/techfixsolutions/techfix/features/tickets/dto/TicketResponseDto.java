package com.techfixsolutions.techfix.features.tickets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.tickets.models.Priority;
import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import com.techfixsolutions.techfix.features.users.models.User;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record TicketResponseDto(
    UUID uuid,
    String title,
    String description,
    TicketStatus status,
    Priority priority,
    Category category,
    User client,
    User agent,
    Instant createdAt,
    Instant updatedAt,
    List<CommentResponseDto> comments
) {}

package com.techfixsolutions.techfix.features.tickets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techfixsolutions.techfix.features.categories.dto.CategorySummaryDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.tickets.models.Priority;
import com.techfixsolutions.techfix.features.tickets.models.TicketStatus;
import com.techfixsolutions.techfix.features.users.dto.UserSummaryDto;

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
    CategorySummaryDto category,
    UserSummaryDto client,
    UserSummaryDto agent,
    Instant createdAt,
    Instant updatedAt,
    List<CommentResponseDto> comments
) {}

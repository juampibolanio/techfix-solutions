package com.techfixsolutions.techfix.features.comments.dto;

import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.models.User;

import java.time.Instant;
import java.util.UUID;

public record CommentResponseDto(
    UUID uuid,
    String content,
    Instant createdAt,
    User user,
    Ticket ticket
){}

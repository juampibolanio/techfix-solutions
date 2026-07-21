package com.techfixsolutions.techfix.features.comments.dto;

import com.techfixsolutions.techfix.features.users.dto.UserSummaryDto;

import java.time.Instant;
import java.util.UUID;

public record CommentResponseDto(
    UUID uuid,
    String content,
    Instant createdAt,
    UserSummaryDto user
){}

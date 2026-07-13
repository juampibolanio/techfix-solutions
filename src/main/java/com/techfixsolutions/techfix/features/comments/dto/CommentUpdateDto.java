package com.techfixsolutions.techfix.features.comments.dto;

import java.util.UUID;

public record CommentUpdateDto(
        String content,
        UUID userId,
        UUID ticketId
) { }

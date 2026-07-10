package com.techfixsolutions.techfix.features.comments.mappers;

import com.techfixsolutions.techfix.features.comments.dto.CommentDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentUpdateDto;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentDto dto, User user, Ticket ticket) {
        return Comment.builder()
                .content(dto.content())
                .user(user)
                .ticket(ticket)
                .build();
    }

    public CommentResponseDto toResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getUuid(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUser(),
                comment.getTicket()
        );
    }

    public void updateEntity(Comment comment, CommentUpdateDto dto, User user, Ticket ticket) {
        if (dto.content() != null) {
            comment.setContent(dto.content());
        }

        if (user != null) {
            comment.setUser(user);
        }

        if (ticket != null) {
            comment.setTicket(ticket);
        }
    }
}

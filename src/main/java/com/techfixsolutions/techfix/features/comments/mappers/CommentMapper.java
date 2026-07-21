package com.techfixsolutions.techfix.features.comments.mappers;

import com.techfixsolutions.techfix.features.comments.dto.CommentDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentUpdateDto;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.dto.TicketSummaryDto;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.dto.UserSummaryDto;
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

        var userDto = new UserSummaryDto(
                comment.getUser().getUuid(),
                comment.getUser().getFullName(),
                comment.getUser().getEmail()
        );


        var ticketDto = new TicketSummaryDto(
                comment.getTicket().getUuid(),
                comment.getTicket().getTitle(),
                comment.getTicket().getStatus()
        );

        return new CommentResponseDto(
                comment.getUuid(),
                comment.getContent(),
                comment.getCreatedAt(),
                userDto
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

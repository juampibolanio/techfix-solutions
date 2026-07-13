package com.techfixsolutions.techfix.features.tickets.mappers;

import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.comments.mappers.CommentMapper;
import com.techfixsolutions.techfix.features.tickets.dto.TicketDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketResponseDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketUpdateDto;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketMapper {
    private final CommentMapper commentMapper;

    public Ticket toEntity(TicketDto dto, User client, User agent, Category category) {
        return Ticket.builder()
                .title(dto.title())
                .description(dto.description())
                .status(dto.status())
                .priority(dto.priority())
                .category(category)
                .client(client)
                .agent(agent)
                .build();
    }

    public TicketResponseDto toResponseDto(Ticket ticket, List<Comment> comments) {
        List<CommentResponseDto> commentsDtos = null;
        if (comments != null) {
            commentsDtos = comments.stream()
                    .map(commentMapper::toResponseDto)
                    .toList();
        }

        return new TicketResponseDto(
                ticket.getUuid(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCategory(),
                ticket.getClient(),
                ticket.getAgent(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                commentsDtos
        );
    }

    public void updateEntity(Ticket ticket, TicketUpdateDto dto, User client, User agent, Category category) {
        if (dto.title() != null) {
            ticket.setTitle(dto.title());
        }

        if (dto.description() != null) {
            ticket.setDescription(dto.description());
        }

        if (dto.status() != null) {
            ticket.setStatus(dto.status());
        }

        if (dto.priority() != null) {
            ticket.setPriority(dto.priority());
        }

        if (category != null) {
            ticket.setCategory(category);
        }

        if (client != null) {
            ticket.setClient(client);
        }

        if (agent != null) {
            ticket.setAgent(agent);
        }
    }
}

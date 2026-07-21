package com.techfixsolutions.techfix.features.tickets.mappers;

import com.techfixsolutions.techfix.features.categories.dto.CategorySummaryDto;
import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.comments.mappers.CommentMapper;
import com.techfixsolutions.techfix.features.tickets.dto.*;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.dto.UserSummaryDto;
import com.techfixsolutions.techfix.features.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketMapper {
    private final CommentMapper commentMapper;

    public Ticket toEntity(TicketDto dto, User client, User agent, Category category) {
        Ticket.TicketBuilder builder = Ticket.builder()
                .title(dto.title())
                .description(dto.description())
                .category(category)
                .client(client)
                .agent(agent);

        if (dto.status() != null) {
            builder.status(dto.status());
        }
        if (dto.priority() != null) {
            builder.priority(dto.priority());
        }
        return builder.build();
    }

    public TicketResponseDto toResponseDto(Ticket ticket, List<Comment> comments) {
        List<CommentResponseDto> commentsDtos = null;
        if (comments != null) {
            commentsDtos = comments.stream()
                    .map(commentMapper::toResponseDto)
                    .toList();
        }

        CategorySummaryDto categoryDto = new CategorySummaryDto(
                ticket.getCategory().getUuid(),
                ticket.getCategory().getName()
        );

        UserSummaryDto clientDto = new UserSummaryDto(
                ticket.getClient().getUuid(),
                ticket.getClient().getFullName(),
                ticket.getClient().getEmail()
        );

        UserSummaryDto agentDto = ticket.getAgent() != null ? new UserSummaryDto(
                ticket.getAgent().getUuid(),
                ticket.getAgent().getFullName(),
                ticket.getAgent().getEmail()
        ) : null;

        return new TicketResponseDto(
                ticket.getUuid(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                categoryDto,
                clientDto,
                agentDto,
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

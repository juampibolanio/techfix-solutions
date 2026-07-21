package com.techfixsolutions.techfix.features.tickets;

import com.techfixsolutions.techfix.features.categories.CategoryRepository;
import com.techfixsolutions.techfix.features.categories.exceptions.CategoryNotFoundException;
import com.techfixsolutions.techfix.features.categories.models.Category;
import com.techfixsolutions.techfix.features.comments.CommentRepository;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.dto.AssignAgentTicketDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketResponseDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketStatusDto;
import com.techfixsolutions.techfix.features.tickets.exceptions.TicketNotFoundException;
import com.techfixsolutions.techfix.features.tickets.mappers.TicketMapper;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.UserRepository;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import com.techfixsolutions.techfix.features.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final CategoryRepository categoryRepository;
    private final TicketRepository ticketRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TicketMapper mapper;

    public Page<TicketResponseDto> findAll(Pageable pageable) {
        Page<Ticket> ticketPage = ticketRepository.findAll(pageable);

        return ticketPage.map(ticket -> mapper.toResponseDto(ticket, null));
    }

    public TicketResponseDto findById(UUID uuid) {
        Ticket ticket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + uuid + " not found"));

        List<Comment> comments = commentRepository.findByTicketUuidOrderByCreatedAtAsc(uuid);

        return mapper.toResponseDto(ticket, comments);
    }


    public TicketResponseDto create(TicketDto dto) {
        User client = userRepository.findById(dto.clientId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        User agent = null;
        if (dto.agentId() != null) {
            agent = userRepository.findById(dto.agentId())
                    .orElseThrow(() -> new UserNotFoundException("Agent not found"));
        }

        Ticket ticket = mapper.toEntity(dto, client, agent, category);

        Ticket savedTicket = ticketRepository.save(ticket);

        return mapper.toResponseDto(savedTicket, null);
    }

    public TicketResponseDto changeTicketStatus(UUID uuid, TicketStatusDto dto) {
        Ticket ticket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticket.setStatus(dto.status());
        Ticket updatedTicket = ticketRepository.save(ticket);

        return mapper.toResponseDto(updatedTicket, null);
    }

    public TicketResponseDto assignAgent(UUID uuid, AssignAgentTicketDto dto) {
        Ticket ticket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        User agent = userRepository.findById(dto.agentUuid())
                .orElseThrow(() -> new UserNotFoundException("Agent not found"));

        ticket.setAgent(agent);
        ticketRepository.save(ticket);
        return mapper.toResponseDto(ticket, null);
    }
}

package com.techfixsolutions.techfix.features.comments;

import com.techfixsolutions.techfix.features.comments.dto.CommentDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentUpdateDto;
import com.techfixsolutions.techfix.features.comments.exceptions.CommentNotFoundException;
import com.techfixsolutions.techfix.features.comments.mappers.CommentMapper;
import com.techfixsolutions.techfix.features.comments.models.Comment;
import com.techfixsolutions.techfix.features.tickets.TicketRepository;
import com.techfixsolutions.techfix.features.tickets.exceptions.TicketNotFoundException;
import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import com.techfixsolutions.techfix.features.users.UserRepository;
import com.techfixsolutions.techfix.features.users.exceptions.UserNotFoundException;
import com.techfixsolutions.techfix.features.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final CommentMapper mapper;

    public List<CommentResponseDto> findAllByTicketId(UUID ticketUuid) {
        List<Comment> comments = commentRepository.findByTicketUuidOrderByCreatedAtAsc(ticketUuid);

        return comments.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    public CommentResponseDto create(UUID ticketUuid, CommentDto dto) {
        Ticket ticket = ticketRepository.findById(ticketUuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + dto.ticketId() + " not found"));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + dto.userId() + " not found"));

        Comment mappedComment = mapper.toEntity(dto, user, ticket);

        Comment savedComment = commentRepository.save(mappedComment);

        return mapper.toResponseDto(savedComment);
    }

    public CommentResponseDto patch(UUID uuid, CommentUpdateDto dto) {
        Ticket ticket = null;
        User user = null;

        Comment currentComment = commentRepository.findById(uuid)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id" + uuid + " not found"));

        if (dto.ticketId() != null) {
            ticket = ticketRepository.findById(dto.ticketId())
                    .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + dto.ticketId() + " not found"));
        }

        if (dto.userId() != null) {
            user = userRepository.findById(dto.userId())
                    .orElseThrow(() -> new UserNotFoundException("User with id " + dto.userId() + " not found"));
        }

        mapper.updateEntity(currentComment, dto, user, ticket);

        Comment updatedComment = commentRepository.save(currentComment);

        return mapper.toResponseDto(updatedComment);
    }

    public void delete(UUID uuid) {
        if (!commentRepository.existsById(uuid)) {
            throw new CommentNotFoundException("Comment with id" + uuid + " not found");
        }
        commentRepository.deleteById(uuid);
    }
}

package com.techfixsolutions.techfix.features.tickets;

import com.techfixsolutions.techfix.features.comments.CommentService;
import com.techfixsolutions.techfix.features.comments.dto.CommentDto;
import com.techfixsolutions.techfix.features.comments.dto.CommentResponseDto;
import com.techfixsolutions.techfix.features.tickets.dto.AssignAgentTicketDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketResponseDto;
import com.techfixsolutions.techfix.features.tickets.dto.TicketStatusDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(ticketService.findAll(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TicketResponseDto> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(ticketService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDto> create(@Valid @RequestBody TicketDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticketService.create(dto));
    }

    @PostMapping("{uuid}/comments")
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable UUID uuid,
            @Valid @RequestBody CommentDto dto) {
        CommentResponseDto createdComment = commentService.create(uuid, dto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PatchMapping("{uuid}/status")
    public ResponseEntity<TicketResponseDto> updateStatus(
            @Valid @PathVariable UUID uuid,
            @Valid @RequestBody TicketStatusDto dto) {
        return ResponseEntity.ok(ticketService.changeTicketStatus(uuid, dto));
    }

    @PatchMapping("{uuid}/assign")
    public ResponseEntity<TicketResponseDto> assignAgent(
            @Valid @PathVariable UUID uuid,
            @Valid @RequestBody AssignAgentTicketDto dto) {
        return ResponseEntity.ok(ticketService.assignAgent(uuid, dto));
    }
}

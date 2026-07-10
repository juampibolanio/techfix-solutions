package com.techfixsolutions.techfix.features.comments;

import com.techfixsolutions.techfix.features.comments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByTicketUuidOrderByCreatedAtAsc(UUID ticketUuid);
}

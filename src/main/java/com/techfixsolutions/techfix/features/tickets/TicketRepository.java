package com.techfixsolutions.techfix.features.tickets;

import com.techfixsolutions.techfix.features.tickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}

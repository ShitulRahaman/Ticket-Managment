package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType,Long> {
}

package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.TicketCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCartRepository extends JpaRepository<TicketCart,Long> {
}

package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Dao.Buyer;
import com.torpedolabs.ticketbackend.ticket.Dao.Ticket;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByArrangement(Arrangement arrangement);

    Long countByArrangement(Arrangement arrangement);

    List<Ticket> findByStatus(ProcessStatus status);

    @Query("SELECT SUM(ticket.totalFare) FROM Ticket ticket WHERE ticket.status=?1")
    Long SumTotalTicketSell(ProcessStatus status);

    Long countAllByStatus(ProcessStatus status);

}

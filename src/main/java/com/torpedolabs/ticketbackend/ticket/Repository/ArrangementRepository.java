package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Dao.TicketType;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement,Long> {
    Long countByIdAndSeatsStatus(Long id,ProcessStatus status);
     List<Arrangement> findByTypeAndStartDateTimeAfter(TicketType ticketType, LocalDateTime  localDateTime);
     List<Arrangement> findByStartDateTimeAfter(LocalDateTime  localDateTime);
     List<Arrangement> findByType(TicketType ticketType);

}

package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import org.springframework.http.ResponseEntity;


public interface TicketService {

    ResponseEntity<?> Save(TicketRequest ticketRequest);

    ResponseEntity<?> Update(TicketRequest ticketRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();

    void TicketRefund(Arrangement arrangement);
    void TicketRefund(Long id);
    void SeatRefund(Long ticketId,Long id);
}

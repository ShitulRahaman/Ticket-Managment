package com.torpedolabs.ticketbackend.ticket.Service;


import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketTypeRequest;
import org.springframework.http.ResponseEntity;



public interface TicketTypeService {
    ResponseEntity<?> Save(TicketTypeRequest ticketTypeRequest);

    ResponseEntity<?> Update(TicketTypeRequest ticketTypeRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();
}

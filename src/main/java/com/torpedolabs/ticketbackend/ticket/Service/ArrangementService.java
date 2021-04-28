package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Dao.TicketType;
import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.SearchTicketForBuyRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ArrangementService {
    ResponseEntity<?> Save(ArrangementRequest arrangementRequest);

    ResponseEntity<?> Update(ArrangementRequest arrangementRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();
    ResponseEntity<?> Count();
    ResponseEntity<?> FindByTicketType(Long ticketTypeId);
    ResponseEntity<?> FindByStartTime(LocalDateTime localDateTime);
    ResponseEntity<?> FindBySearch(SearchTicketForBuyRequest searchTicketForBuyRequest);


}

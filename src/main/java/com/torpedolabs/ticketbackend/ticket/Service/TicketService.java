package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Model.Request.SearchTicketForBuyRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.http.ResponseEntity;


public interface TicketService {

    ResponseEntity<?> Save(TicketRequest ticketRequest);

    ResponseEntity<?> Update(TicketRequest ticketRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();

    void TicketRefund(Arrangement arrangement);

    ResponseEntity<?> TicketRefund(Long id);

    ResponseEntity<?> SeatRefund(Long ticketId, Long id);

    ResponseEntity<?> TicketSearchForBuy(SearchTicketForBuyRequest searchTicketForBuyRequest);

    ResponseEntity<?> TicketSearchByBuyerPhone();

    ResponseEntity<?> TicketTotalSale();

    ResponseEntity<?> TicketSoldCount();

    ResponseEntity<?> TicketRefundCount();

    ResponseEntity<?> TicketCount();
    ResponseEntity<?> FoundTicket(ProcessStatus status);
}

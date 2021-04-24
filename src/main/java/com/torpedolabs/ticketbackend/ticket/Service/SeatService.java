package com.torpedolabs.ticketbackend.ticket.Service;


import com.torpedolabs.ticketbackend.ticket.Model.Request.SeatRequest;
import org.springframework.http.ResponseEntity;



public interface SeatService {

    ResponseEntity<?> Save(SeatRequest seatRequest);

    ResponseEntity<?> Update(SeatRequest seatRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();
}

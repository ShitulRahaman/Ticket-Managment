package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.LocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface LocationService {
    ResponseEntity<?> Save(LocationRequest locationRequest);

    ResponseEntity<?> Update(LocationRequest locationRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();
}

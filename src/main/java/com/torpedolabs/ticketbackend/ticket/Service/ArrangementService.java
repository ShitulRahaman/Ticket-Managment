package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface ArrangementService {
    ResponseEntity<?> Save(ArrangementRequest arrangementRequest);

    ResponseEntity<?> Update(ArrangementRequest arrangementRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();
}

package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;

import java.util.List;
import java.util.Optional;


public interface ArrangementService {
    void Save(ArrangementRequest arrangementRequest);
    void Update(ArrangementRequest arrangementRequest);
    Optional<Arrangement> Get(Long id);
    void Delete(Long id);
    List<Arrangement> Gets();
}

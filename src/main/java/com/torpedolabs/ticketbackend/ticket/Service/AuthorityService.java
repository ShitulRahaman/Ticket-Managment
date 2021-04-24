package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Model.Request.AuthorityRequest;
import org.springframework.http.ResponseEntity;

public interface AuthorityService {
    ResponseEntity<?> Save(AuthorityRequest authorityRequest);

    ResponseEntity<?> Update(AuthorityRequest authorityRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Gets();
}

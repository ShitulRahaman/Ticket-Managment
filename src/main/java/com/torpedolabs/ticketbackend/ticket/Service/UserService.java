package com.torpedolabs.ticketbackend.ticket.Service;

import com.torpedolabs.ticketbackend.ticket.Dao.User;
import com.torpedolabs.ticketbackend.ticket.Model.Request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    ResponseEntity<?> Save(UserRequest userRequest);

    ResponseEntity<?> Update(UserRequest userRequest);

    ResponseEntity<?> Get(Long id);

    ResponseEntity<?> Delete(Long id);

    ResponseEntity<?> Gets();

    Optional<User> getUserWithAuthorities();
}

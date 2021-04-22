package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}

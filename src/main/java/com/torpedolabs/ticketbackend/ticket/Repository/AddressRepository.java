package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}

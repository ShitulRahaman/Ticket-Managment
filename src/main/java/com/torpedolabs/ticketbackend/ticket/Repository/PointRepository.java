package com.torpedolabs.ticketbackend.ticket.Repository;

import com.torpedolabs.ticketbackend.ticket.Dao.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point,Long> {
}

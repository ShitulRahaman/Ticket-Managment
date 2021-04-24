package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Dao.Location;
import com.torpedolabs.ticketbackend.ticket.Dao.Seat;
import com.torpedolabs.ticketbackend.ticket.Dao.TicketType;
import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Model.ResponseMessage;
import com.torpedolabs.ticketbackend.ticket.Repository.*;
import com.torpedolabs.ticketbackend.ticket.Service.ArrangementService;
import com.torpedolabs.ticketbackend.ticket.Service.TicketService;
import com.torpedolabs.ticketbackend.ticket.Utility.LocalDateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;


    @Override
    public ResponseEntity<?> Save(ArrangementRequest arrangementRequest) {
        Arrangement arrangement = new Arrangement(arrangementRequest);

        //Set Ticket Type
        ticketTypeRepository.findById(arrangementRequest.getType()).ifPresent(ticketType -> arrangement.setType(ticketType));

        //Set Ticket Seat
        Set<Seat> seats = new HashSet<Seat>();
        for (Long seatId : arrangementRequest.getSeats()) {
            seatRepository.findById(seatId).ifPresent(seat -> seats.add(seat));
        }
        arrangement.setSeats(seats);

        //Set Location
        Set<Location> locations = new HashSet<Location>();
        for (Long locationId : arrangementRequest.getLocations()) {
            locationRepository.findById(locationId).ifPresent(location -> locations.add(location));
        }
        arrangement.setLocations(locations);

        arrangementRepository.save(arrangement);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(ArrangementRequest arrangementRequest) {
        ResponseMessage responseMessage = new ResponseMessage("Update", "Arrangement Not Found");

        arrangementRepository.findById(arrangementRequest.getId()).ifPresent(arrangement -> {
            if (ticketRepository.countByArrangement(arrangement) == 0) {
                //Update Data
                arrangement.setName(arrangementRequest.getName());
                arrangement.setDescription(arrangementRequest.getDescription());
                arrangement.setTotalSeats(arrangementRequest.getTotalSeats());
                arrangement.setReportingDateTime(LocalDateTimeConverter.DateTime(arrangementRequest.getReportingDateTime()));
                arrangement.setStartDateTime(LocalDateTimeConverter.DateTime(arrangementRequest.getStartDateTime()));
                arrangement.setEndDateTime(LocalDateTimeConverter.DateTime(arrangementRequest.getEndDateTime()));

                //Location Update
                Set<Location> locations = new HashSet<Location>();
                for (Long locationId : arrangementRequest.getLocations()) {
                    locationRepository.findById(locationId).ifPresent(location -> locations.add(location));
                }
                arrangement.setLocations(locations);

                //Update Ticket Seat
                Set<Seat> seats = new HashSet<Seat>();
                for (Long seatId : arrangementRequest.getSeats()) {
                    seatRepository.findById(seatId).ifPresent(seat -> seats.add(seat));
                }
                arrangement.setSeats(seats);

                //Update Ticket Type
                ticketTypeRepository.findById(arrangementRequest.getType()).ifPresent(ticketType -> arrangement.setType(ticketType));

                arrangementRepository.save(arrangement);
                responseMessage.setMessage("Arrangement Update Successfully");
            } else
                responseMessage.setMessage("Can't be change ticket already sold");

        });

        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(arrangementRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        arrangementRepository.findById(id).ifPresent(arrangement -> {
            arrangement.setActive(false);
            if (arrangement.getStartDateTime().isAfter(LocalDateTime.now()) && ticketRepository.countByArrangement(arrangement) != 0)
                //Already Sold Ticket Should Be Refunded
                ticketService.TicketRefund(arrangement);
            arrangementRepository.save(arrangement);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(arrangementRepository.findAll());
    }
}

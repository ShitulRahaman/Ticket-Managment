package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Dao.Seat;
import com.torpedolabs.ticketbackend.ticket.Dao.Ticket;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Model.ResponseMessage;
import com.torpedolabs.ticketbackend.ticket.Repository.ArrangementRepository;
import com.torpedolabs.ticketbackend.ticket.Repository.SeatRepository;
import com.torpedolabs.ticketbackend.ticket.Repository.TicketRepository;
import com.torpedolabs.ticketbackend.ticket.Repository.UserRepository;
import com.torpedolabs.ticketbackend.ticket.Service.TicketService;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<?> Save(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(ticketRequest);

        //Set Arrangement
        arrangementRepository.findById(ticketRequest.getArrangement()).ifPresent(arrangement -> ticket.setArrangement(arrangement));
        //Set User
        userRepository.findById(ticketRequest.getUser()).ifPresent(user -> ticket.setUser(user));
        //Set Ticket Seat
        Set<Seat> seats = new HashSet<Seat>();
        for (Long seatId : ticketRequest.getSeats()) {
            seatRepository.findById(seatId).ifPresent(seat -> {
                seats.add(seat);
                ticket.setTotalFare(ticket.getTotalFare()+seat.getFare());
            });
        }
        ticket.setSeats(seats);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(TicketRequest ticketRequest) {
        ResponseMessage responseMessage=new ResponseMessage("Update","Ticket Not Found");
        ticketRepository.findById(ticketRequest.getId()).ifPresent(ticket -> {
            ticket.setSerial(ticketRequest.getSerial());
            ticket.setComment(ticketRequest.getComment());
            ticket.setTotalFare(ticketRequest.getTotalFare());
            //Set Arrangement
            arrangementRepository.findById(ticketRequest.getArrangement()).ifPresent(arrangement -> ticket.setArrangement(arrangement));
            //Set User
            userRepository.findById(ticketRequest.getUser()).ifPresent(user -> ticket.setUser(user));
            //Set Ticket Seat
            Set<Seat> seats = new HashSet<Seat>();
            for (Long seatId : ticketRequest.getSeats()) {
                seatRepository.findById(seatId).ifPresent(seat -> seats.add(seat));
            }
            ticket.setSeats(seats);
            ticketRepository.save(ticket);
            responseMessage.setMessage("Ticket Update Successfully");

        });
        return ResponseEntity.ok(responseMessage);
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(ticketRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    @Override
    public void TicketRefund(Arrangement arrangement) {
        for (Ticket ticket : ticketRepository.findByArrangement(arrangement)) {
            ticket.setStatus(ProcessStatus.REFUND);
            //Open Sold Or Booking Seat
            for(Seat ticketSeat : ticket.getSeats()){
                ticketSeat.setStatus(ProcessStatus.OPEN);
                seatRepository.save(ticketSeat);
                ticket.setTotalFare(ticket.getTotalFare()-ticketSeat.getFare());
                ticket.getSeats().remove(ticketSeat);
            }
            ticketRepository.save(ticket);
        }
    }

    @Override
    public void TicketRefund(Long id) {
        ticketRepository.findById(id).ifPresent(ticket -> {
            ticket.setStatus(ProcessStatus.REFUND);
            //Open Sold Or Booking Seat
            for(Seat Seat : ticket.getSeats()){
                Seat.setStatus(ProcessStatus.OPEN);
                seatRepository.save(Seat);
                ticket.setTotalFare(ticket.getTotalFare()-Seat.getFare());
                ticket.getSeats().remove(Seat);
            }


        });
    }

    @Override
    public void SeatRefund(Long ticketId,Long seatId) {
       // ticketId
        seatRepository.findById(seatId).ifPresent(seat -> {
            seat.setStatus(ProcessStatus.OPEN);
            seatRepository.save(seat);
            ticketRepository.findById(ticketId).ifPresent(ticket -> {
                ticket.getSeats().remove(seat);
                ticket.setTotalFare(ticket.getTotalFare()-seat.getFare());
            });
        });
    }
}

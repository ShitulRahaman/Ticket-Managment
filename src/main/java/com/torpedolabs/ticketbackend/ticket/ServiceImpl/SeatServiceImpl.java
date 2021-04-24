package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Seat;
import com.torpedolabs.ticketbackend.ticket.Model.Request.SeatRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.SeatRepository;
import com.torpedolabs.ticketbackend.ticket.Service.SeatService;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Override
    public ResponseEntity<?> Save(SeatRequest seatRequest) {
        Seat seat=new Seat(seatRequest);
        seatRepository.save(seat);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(SeatRequest seatRequest) {
        seatRepository.findById(seatRequest.getId()).ifPresent(seat -> {
        seat.setName(seatRequest.getName());
                    seat.setDescription(seatRequest.getDescription());
        seat.setStatus(ProcessStatus.OPEN.getProcessStatus(seatRequest.getStatus()));
        seat.setFare(seatRequest.getFare());
        seat.setActive(seatRequest.isActive());
        seatRepository.save(seat);
        }

        );
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(seatRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        seatRepository.findById(id).ifPresent(seat -> {
            seat.setActive(false);
            seatRepository.save(seat);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(seatRepository.findAll());
    }
}

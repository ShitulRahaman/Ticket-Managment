package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.TicketType;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketTypeRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.TicketTypeRepository;
import com.torpedolabs.ticketbackend.ticket.Service.TicketTypeService;
import com.torpedolabs.ticketbackend.ticket.Utility.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketTypeServiceImpl implements TicketTypeService {

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Override
    public ResponseEntity<?> Save(TicketTypeRequest ticketTypeRequest) {
        TicketType ticketType = new TicketType(ticketTypeRequest);
        ticketTypeRepository.save(ticketType);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(TicketTypeRequest ticketTypeRequest) {
        ticketTypeRepository.findById(ticketTypeRequest.getId()).ifPresent(ticketType -> {
            ticketType.setName( ticketTypeRequest.getName());
            ticketType.setProgram( Program.TRAVELS.getProgram(ticketTypeRequest.getProgram()));
            ticketType.setDescription(  ticketTypeRequest.getDescription());
            ticketType.setActive(ticketTypeRequest.isActive());
            ticketTypeRepository.save(ticketType);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(ticketTypeRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        ticketTypeRepository.findById(id).ifPresent(ticketType -> {
            ticketType.setActive(false);
            ticketTypeRepository.save(ticketType);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(ticketTypeRepository.findAll());
    }
}

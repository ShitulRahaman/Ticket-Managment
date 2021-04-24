package com.torpedolabs.ticketbackend.ticket.Controller;

import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketTypeRequest;
import com.torpedolabs.ticketbackend.ticket.Service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketType")
public class TicketTypeController {
    @Autowired
    TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody TicketTypeRequest ticketTypeRequest) {
        return ticketTypeService.Save(ticketTypeRequest);
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(ticketTypeService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return ticketTypeService.Get(id);
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody TicketTypeRequest ticketTypeRequest) {
        return ticketTypeService.Update(ticketTypeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return ticketTypeService.Delete(id);
    }
}

package com.torpedolabs.ticketbackend.ticket.Controller;


import com.torpedolabs.ticketbackend.ticket.Dao.Ticket;
import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody TicketRequest ticketRequest){
        return ticketService.Save(ticketRequest);
    }
    @GetMapping
    public ResponseEntity<?> Gets(){
        return ticketService.Gets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id){
        return  ticketService.Get(id);
    }
    @PutMapping
    public ResponseEntity<?> Update(@RequestBody TicketRequest ticketRequest){
        return  ticketService.Update(ticketRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        return ticketService.Delete(id);
    }
}

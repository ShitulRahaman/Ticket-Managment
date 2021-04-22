package com.torpedolabs.ticketbackend.ticket.Controller;


import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody TicketRequest ticketRequest){
        // System.out.println(arrangementRequest);
        return  ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> Gets(){
        /// System.out.println("GetAll");
        return  ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id){
        System.out.println("Get id"+id);
        return  ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<?> Update(@RequestBody TicketRequest ticketRequest){
        // System.out.println(arrangementRequest+"Put id");
        return  ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        // System.out.println("Delete id"+id);
        return  ResponseEntity.ok().build();
    }
}

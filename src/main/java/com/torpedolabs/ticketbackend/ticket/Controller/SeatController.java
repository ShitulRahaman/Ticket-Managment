package com.torpedolabs.ticketbackend.ticket.Controller;

import com.torpedolabs.ticketbackend.ticket.Model.Request.SeatRequest;
import com.torpedolabs.ticketbackend.ticket.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody SeatRequest seatRequest) {
        return seatService.Save(seatRequest);
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(seatService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return seatService.Get(id);
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody SeatRequest seatRequest) {
        return seatService.Update(seatRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return seatService.Delete(id);
    }
}

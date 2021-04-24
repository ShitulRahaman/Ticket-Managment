package com.torpedolabs.ticketbackend.ticket.Controller;


import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/arrangement")
public class ArrangementController {

    @Autowired
    ArrangementService arrangementService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody ArrangementRequest arrangementRequest) {
        return arrangementService.Save(arrangementRequest);
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(arrangementService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return arrangementService.Get(id);
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody ArrangementRequest arrangementRequest) {
        return arrangementService.Update(arrangementRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return arrangementService.Delete(id);
    }

}

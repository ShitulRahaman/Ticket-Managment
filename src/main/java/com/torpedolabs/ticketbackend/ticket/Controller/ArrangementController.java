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
        arrangementService.Save(arrangementRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(arrangementService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return ResponseEntity.of(arrangementService.Get(id));
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody ArrangementRequest arrangementRequest) {
        arrangementService.Update(arrangementRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        arrangementService.Delete(id);
        return ResponseEntity.ok().build();
    }

}

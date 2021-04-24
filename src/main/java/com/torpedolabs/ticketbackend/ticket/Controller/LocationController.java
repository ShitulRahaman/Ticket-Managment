package com.torpedolabs.ticketbackend.ticket.Controller;

import com.torpedolabs.ticketbackend.ticket.Model.Request.LocationRequest;
import com.torpedolabs.ticketbackend.ticket.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody LocationRequest locationRequest) {
        return locationService.Save(locationRequest);
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(locationService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return locationService.Get(id);
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody LocationRequest locationRequest) {
        return locationService.Update(locationRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return locationService.Delete(id);
    }


}

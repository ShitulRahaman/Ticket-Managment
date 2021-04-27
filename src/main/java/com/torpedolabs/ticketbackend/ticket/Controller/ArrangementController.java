package com.torpedolabs.ticketbackend.ticket.Controller;


import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Model.Request.SearchTicketForBuyRequest;
import com.torpedolabs.ticketbackend.ticket.Service.ArrangementService;
import com.torpedolabs.ticketbackend.ticket.Utility.LocalDateTimeConverter;
import jdk.nashorn.internal.objects.annotations.Getter;
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

    @PostMapping("/findByType")
    public ResponseEntity<?> FindByType(@RequestBody SearchTicketForBuyRequest searchTicketForBuyRequest) {
        return arrangementService.FindByTicketType(searchTicketForBuyRequest.getTicketType());
    }

    @PostMapping("/findByStartTime")
    public ResponseEntity<?> FindByTime(@RequestBody SearchTicketForBuyRequest searchTicketForBuyRequest) {
        return arrangementService.FindByStartTime(LocalDateTimeConverter.DateTime(searchTicketForBuyRequest.getDateTime()));
    }
    @GetMapping("/count")
    public ResponseEntity<?> Count() {
        return arrangementService.Count();
    }

}

package com.torpedolabs.ticketbackend.ticket.Controller;

import com.torpedolabs.ticketbackend.ticket.Model.Request.AuthorityRequest;
import com.torpedolabs.ticketbackend.ticket.Service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @PostMapping
    public ResponseEntity<?> Create(@RequestBody AuthorityRequest authorityRequest) {
        return authorityService.Save(authorityRequest);
    }

    @GetMapping
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(authorityService.Gets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Get(@PathVariable Long id) {
        return authorityService.Get(id);
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody AuthorityRequest authorityRequest) {
        return authorityService.Update(authorityRequest);
    }


}

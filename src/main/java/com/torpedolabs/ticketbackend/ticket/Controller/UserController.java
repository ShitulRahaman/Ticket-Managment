package com.torpedolabs.ticketbackend.ticket.Controller;

import com.torpedolabs.ticketbackend.ticket.Model.Request.UserRequest;
import com.torpedolabs.ticketbackend.ticket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> Create(@RequestBody UserRequest userRequest){
        return userService.Save(userRequest) ;
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
    public ResponseEntity<?> Update(@RequestBody UserRequest userRequest){
        // System.out.println(arrangementRequest+"Put id");
        return  ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        // System.out.println("Delete id"+id);
        return  ResponseEntity.ok().build();
    }
}

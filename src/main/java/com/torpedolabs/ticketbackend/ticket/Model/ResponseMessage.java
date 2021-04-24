package com.torpedolabs.ticketbackend.ticket.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@ToString
public class ResponseMessage  {
    private String title;
    private String message;

    public ResponseMessage(String title,String message){
        this.title=title;
        this.message=message;
    }
}

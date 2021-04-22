package com.torpedolabs.ticketbackend.ticket.Model.Request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketTypeRequest {
    private Long id;
    private String name;
    private Integer program;
    private String description;
}

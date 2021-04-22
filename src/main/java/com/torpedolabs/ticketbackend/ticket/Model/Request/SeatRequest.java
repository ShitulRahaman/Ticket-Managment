package com.torpedolabs.ticketbackend.ticket.Model.Request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SeatRequest {
    private Long id;
    private String name;
    private String description;
    private Integer status;
}

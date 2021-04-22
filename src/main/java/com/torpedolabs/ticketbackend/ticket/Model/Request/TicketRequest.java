package com.torpedolabs.ticketbackend.ticket.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TicketRequest {
    private Long id;
    private Integer status;
    private Long totalFare;
    private List<Long> seats;
    private Long arrangement;
    private Long user;
}

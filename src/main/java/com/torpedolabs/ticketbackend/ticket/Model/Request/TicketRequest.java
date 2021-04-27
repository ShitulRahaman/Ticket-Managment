package com.torpedolabs.ticketbackend.ticket.Model.Request;

import com.torpedolabs.ticketbackend.ticket.Dao.Buyer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TicketRequest {
    private Long id;
    private String serial;
    private String comment;
    private Integer status;
    private Long totalFare;
    private List<Long> seats;
    private Long arrangement;
    private BuyerRequest buyerRequest;
}

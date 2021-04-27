package com.torpedolabs.ticketbackend.ticket.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyerRequest {
    private Long id;
    private String name;
    private String phone;
    private String email;
}

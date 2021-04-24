package com.torpedolabs.ticketbackend.ticket.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationRequest {
    private Long id;
    private String name;
    private String area;
    private String street;
    private String city;
    private String country;
    private boolean active;
}

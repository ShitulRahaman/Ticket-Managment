package com.torpedolabs.ticketbackend.ticket.Model.Request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class ArrangementRequest {
    private Long id;
    private String serial;
    private Integer totalSeats;
    private Integer availableSeats;
    private Long fare;
    private String reportingDateTime;
    private String startDateTime;
    private String endDateTime;
    private Long type;
    private List<LocationRequest> locations;
    private List<SeatRequest> seats;
}

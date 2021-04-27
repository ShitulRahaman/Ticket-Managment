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
    private String name;
    private String description;
    private Integer totalSeats;
    private String reportingDateTime;
    private String startDateTime;
    private String endDateTime;
    private Long type;
    private List<Long> locations;
    private List<SeatRequest> seats;
    private boolean active;
}

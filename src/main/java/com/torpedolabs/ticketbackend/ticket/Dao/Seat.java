package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SEAT")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private ProcessStatus status;
}

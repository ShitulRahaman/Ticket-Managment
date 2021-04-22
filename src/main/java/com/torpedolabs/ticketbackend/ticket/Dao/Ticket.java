package com.torpedolabs.ticketbackend.ticket.Dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKET")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial;
    private Integer totalSeats;
    private Long fare;
    private LocalDate date;
    private LocalTime reportingTime;
    private LocalTime startTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type_id", referencedColumnName = "id")
    private TicketType type;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Point> points;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Seat> seats;
}

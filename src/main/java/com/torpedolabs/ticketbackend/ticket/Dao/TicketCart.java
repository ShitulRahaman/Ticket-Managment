package com.torpedolabs.ticketbackend.ticket.Dao;


import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKET_CART")
public class TicketCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProcessStatus status;
    private Long totalFare;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Seat> seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}

package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Utility.Program;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKET_TYPE")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Program program;
    private String description;
}

package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Utility.Program;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKET_TYPE")
public class TicketType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Program program;
    private String description;

    @UpdateTimestamp
    private Timestamp lastUpdatedStamp;
    @UpdateTimestamp
    private Timestamp lastUpdatedTxStamp;
    @CreationTimestamp
    private Timestamp createdStamp;
    @CreationTimestamp
    private Timestamp createdTxStamp;
}

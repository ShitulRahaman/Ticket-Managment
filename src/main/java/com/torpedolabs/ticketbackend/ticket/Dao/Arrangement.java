package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Utility.LocalDateTimeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ARRANGEMENT")
public class Arrangement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer totalSeats;
    private Long fare;
    private LocalDateTime reportingDateTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean active;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type_id", referencedColumnName = "id")
    private TicketType type;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Location> locations;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Seat> seats;

    @UpdateTimestamp
    private Timestamp lastUpdatedStamp;
    @UpdateTimestamp
    private Timestamp lastUpdatedTxStamp;
    @CreationTimestamp
    private Timestamp createdStamp;
    @CreationTimestamp
    private Timestamp createdTxStamp;


    public Arrangement(ArrangementRequest arrangementRequest){
        this.name=arrangementRequest.getName();
        this.description=arrangementRequest.getDescription();
        this.totalSeats=arrangementRequest.getTotalSeats();
        this.reportingDateTime= LocalDateTimeConverter.DateTime(arrangementRequest.getReportingDateTime());
        this.startDateTime= LocalDateTimeConverter.DateTime(arrangementRequest.getStartDateTime());
        this.endDateTime= LocalDateTimeConverter.DateTime(arrangementRequest.getEndDateTime());
        this.active=true;
    }
}

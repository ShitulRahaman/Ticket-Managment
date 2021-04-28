package com.torpedolabs.ticketbackend.ticket.Dao;


import com.torpedolabs.ticketbackend.ticket.Model.Request.TicketRequest;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
import com.torpedolabs.ticketbackend.ticket.Utility.RandomString;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKET")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serial;
    private String comment;
    private ProcessStatus status;
    private Long totalFare;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Seat> seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrangement_id", referencedColumnName = "id")
    private Arrangement arrangement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    @UpdateTimestamp
    private Timestamp lastUpdatedStamp;
    @UpdateTimestamp
    private Timestamp lastUpdatedTxStamp;
    @CreationTimestamp
    private Timestamp createdStamp;
    @CreationTimestamp
    private Timestamp createdTxStamp;


    public Ticket(TicketRequest ticketRequest){
        this.serial= RandomString.SerialNumber();
        this.comment=ticketRequest.getComment();
        this.status=ProcessStatus.SOLD;
        this.totalFare=ticketRequest.getTotalFare();
    }

}

package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Model.Request.SeatRequest;
import com.torpedolabs.ticketbackend.ticket.Utility.ProcessStatus;
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
@Table(name = "SEAT")
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private ProcessStatus status;
    private Long fare;
    private boolean active;
    @UpdateTimestamp
    private Timestamp lastUpdatedStamp;
    @UpdateTimestamp
    private Timestamp lastUpdatedTxStamp;
    @CreationTimestamp
    private Timestamp createdStamp;
    @CreationTimestamp
    private Timestamp createdTxStamp;

    public Seat(SeatRequest seatRequest){
        this.name=seatRequest.getName();
        this.description=seatRequest.getDescription();
        this.status= ProcessStatus.OPEN.getProcessStatus(seatRequest.getStatus());
        this.fare= seatRequest.getFare();
        this.active= true;
    }
}

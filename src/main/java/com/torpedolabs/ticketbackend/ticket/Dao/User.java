package com.torpedolabs.ticketbackend.ticket.Dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String mobile;
    private boolean active;

    @UpdateTimestamp
    private Timestamp lastUpdatedStamp;
    @UpdateTimestamp
    private Timestamp lastUpdatedTxStamp;
    @CreationTimestamp
    private Timestamp createdStamp;
    @CreationTimestamp
    private Timestamp createdTxStamp;
}

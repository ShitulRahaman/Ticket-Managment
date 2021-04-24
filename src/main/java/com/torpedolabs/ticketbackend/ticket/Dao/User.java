package com.torpedolabs.ticketbackend.ticket.Dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.torpedolabs.ticketbackend.ticket.Model.Request.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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


    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAuthority> authorities = new HashSet<>();

    public User(UserRequest userRequest){
        this.userName=userRequest.getUserName();
        this.password=userRequest.getPassword();
        this.email=userRequest.getEmail();
        this.mobile=userRequest.getMobile();
        this.active=true;

    }
}

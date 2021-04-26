package com.torpedolabs.ticketbackend.ticket.Dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.torpedolabs.ticketbackend.ticket.Model.Request.AuthorityRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTHORITY")
@NoArgsConstructor
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAuthority> authorities = new HashSet<>();

    public  Authority(AuthorityRequest authorityRequest){
        this.name=authorityRequest.getName();
    }
    public  Authority(String name){
        this.name=name;
    }
}

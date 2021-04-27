package com.torpedolabs.ticketbackend.ticket.Dao;

import com.torpedolabs.ticketbackend.ticket.Model.Request.BuyerRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "BUYER")
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;

    public Buyer(BuyerRequest buyerRequest){

        this.name=buyerRequest.getName();
        this.phone=buyerRequest.getPhone();
        this.email=buyerRequest.getEmail();
    }

}

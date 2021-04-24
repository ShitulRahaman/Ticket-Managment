package com.torpedolabs.ticketbackend.ticket.Model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class AuthorityRequest {
    private Long id;
    private String name;
}

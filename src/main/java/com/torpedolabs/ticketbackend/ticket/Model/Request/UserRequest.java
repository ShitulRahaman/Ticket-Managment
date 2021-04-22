package com.torpedolabs.ticketbackend.ticket.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private boolean active;
}

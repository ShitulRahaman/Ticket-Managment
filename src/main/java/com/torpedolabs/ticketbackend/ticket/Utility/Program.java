package com.torpedolabs.ticketbackend.ticket.Utility;

public enum Program {
    TRAVELS(0),
    OTHER(1);

    public final Integer value;

    private Program(Integer value) {
        this.value = value;
    }
}

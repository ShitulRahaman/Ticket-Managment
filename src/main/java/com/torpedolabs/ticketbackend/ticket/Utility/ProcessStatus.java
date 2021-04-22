package com.torpedolabs.ticketbackend.ticket.Utility;

public enum ProcessStatus {
    OPEN(0),
    ON_PROCESSING(1),
    ON_BOOKED(2),
    SOLD(3),
    REFUND(4);

    public final Integer value;

    private ProcessStatus(Integer value) {
        this.value = value;
    }
}

package com.torpedolabs.ticketbackend.ticket.Utility;

public enum ProcessStatus {
    OPEN(0),
    ON_PROCESSING(1),
    ON_BOOKED(2),
    SOLD(3),
    REFUND(4);

    public final Integer value;

    public ProcessStatus getProcessStatus(Integer value)
    {
        switch (value){
            case 0:
                return OPEN;
            case 1:
                return ON_PROCESSING;
            case 2:
                return ON_BOOKED;
            case 3:
                return SOLD;
            case 4:
                return REFUND;

        }
        return OPEN;
    }
    private ProcessStatus(Integer value) {
        this.value = value;
    }
}

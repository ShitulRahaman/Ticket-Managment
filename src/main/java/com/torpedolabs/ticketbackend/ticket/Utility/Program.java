package com.torpedolabs.ticketbackend.ticket.Utility;

public enum Program {
    TRAVELS(0),
    OTHER(1);

    public final Integer value;
    public Program getProgram(Integer value)
    {
        switch (value){
            case 0:
                return TRAVELS;
            case 1:
                return OTHER;
        }
        return TRAVELS;
    }

    private Program(Integer value) {
        this.value = value;
    }
}

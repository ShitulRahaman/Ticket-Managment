package com.torpedolabs.ticketbackend.ticket.Utility;

import com.fasterxml.jackson.databind.util.Converter;
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {

    private static String format="mm/dd/yyyy hh:mm a";

    public static LocalDateTime DateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }
}

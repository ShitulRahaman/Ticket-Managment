package com.torpedolabs.ticketbackend.ticket.Utility;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomString {
    public static String SerialNumber(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}

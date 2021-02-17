package com.chatano.ticketmanager.utility;

public class TicketGenerator {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    public static String generateNewTicket()
    {
        StringBuilder builder = new StringBuilder();
        int count=0;
        while (count!=12) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count++;
        }
        return builder.toString();
    }

}

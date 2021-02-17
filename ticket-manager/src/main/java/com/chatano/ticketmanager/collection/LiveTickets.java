package com.chatano.ticketmanager.collection;

import com.chatano.ticketmanager.utility.TicketGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LiveTickets {

    Map<String,HashSet<String>> liveTickets = new HashMap<>();

    public HashSet<String> getLiveTickets(String application) {
        return liveTickets.get(application);
    }

    public String getNewTicket(String application) {
        String ticket = TicketGenerator.generateNewTicket();
        if(liveTickets.containsKey(application))
        {
            liveTickets.get(application).add(ticket);
        }
        else
        {
            liveTickets.put(application,new HashSet<>(Arrays.asList(ticket)));
        }
        return ticket;
    }

    public boolean removeTicket(String application,String ticket) {
        if (liveTickets.containsKey(application)) {
            if (liveTickets.get(application).contains(ticket)) {
                liveTickets.get(application).remove(ticket);
                return true;
            }
        }
        return false;
    }
}

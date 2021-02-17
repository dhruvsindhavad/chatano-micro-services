package com.chatano.ticketmanager;

import com.chatano.ticketmanager.collection.LiveTickets;
import com.chatano.ticketmanager.utility.TicketGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketManager {

    @Autowired
    LiveTickets liveTickets;


    @RequestMapping("/authenticate/{application}/{ticket}")
    public Boolean authenticate(@PathVariable("application") String application,@PathVariable("ticket") String ticket)
    {
       return liveTickets.removeTicket(application,ticket);
    }

    @RequestMapping("/generate/{application}")
    public String getNewTicket(@PathVariable("application") String application)
    {
        return liveTickets.getNewTicket(application);
    }
}

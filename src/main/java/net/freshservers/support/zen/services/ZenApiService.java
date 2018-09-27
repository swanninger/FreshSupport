package net.freshservers.support.zen.services;

import net.freshservers.support.zen.domain.Ticket;

import java.util.List;

public interface ZenApiService {
    List<Ticket> getTickets();
    void sendTicket(Ticket ticket) throws Exception;
}

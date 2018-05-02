package net.freshservers.support.zen.services;

import net.freshservers.support.zen.configuration.ZenApiConfiguration;
import net.freshservers.support.zen.domain.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ZenApiServiceImpl implements ZenApiService {
    private ZenApiConfiguration zenApiConfiguration;
    private RestTemplate restTemplate;

    public ZenApiServiceImpl(ZenApiConfiguration zenApiConfiguration, RestTemplate restTemplate) {
        this.zenApiConfiguration = zenApiConfiguration;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Ticket> getTickets() {
        return null;
    }

    @Override
    public Ticket createTicket() {
        return null;
    }
}

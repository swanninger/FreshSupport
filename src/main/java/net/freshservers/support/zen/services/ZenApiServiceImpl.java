package net.freshservers.support.zen.services;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.zen.configuration.ZenApiConfiguration;
import net.freshservers.support.zen.domain.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
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
    public void sendTicket (Ticket ticket) throws Exception{
        String url = zenApiConfiguration.getBaseUrl() + "tickets.json";

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("ticket", ticket);

        try {
            restTemplate.postForObject(url, postMap, JsonNode.class);
        } catch (RestClientException e) {
            restTemplate.postForObject(url, postMap, JsonNode.class);
        }
//        log.info(t.toString());

    }
}

package net.freshservers.support;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.zen.domain.Comment;
import net.freshservers.support.zen.domain.Requester;
import net.freshservers.support.zen.domain.Ticket;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class RestTemplateTest {

    private final String apiUrl = "https://bgcpos.zendesk.com/api/v2/tickets.json";

    @Test
    public void postTicket(){
//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .basicAuthorization("scott@freshtechnology.com/token","3eHFJWzNrxknNRiDiEaMI23nR4DZ1zvhS20AboIa")
//                .build();
//
//        Map<String,Object> ticket = new HashMap<>();
//        ticket.put("subject", "API Test");
//        ticket.put("comment", new Comment("This is a test"));
//        ticket.put("group_id","360000932611");
//
//        Map<String, Map<String,Object>> postMap = new HashMap<>();
//        postMap.put("ticket",ticket);
//
//        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
//        log.info(jsonNode.toString());
    }

    @Test
    public void postTicket2(){
//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .basicAuthorization("scott@freshtechnology.com/token","3eHFJWzNrxknNRiDiEaMI23nR4DZ1zvhS20AboIa")
//                .build();
//
//        Ticket ticket = new Ticket("API Test",new Comment("This is a test"), 360000932611L);
//        ticket.setRequester(new Requester("Mark Gilmer","mark@freshtechnology.com"));
//
//        Map<String, Object> postMap = new HashMap<>();
//        postMap.put("ticket", ticket);
//
//        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
//        log.info(jsonNode.toString());
    }

}
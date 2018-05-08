package net.freshservers.support;

import com.fasterxml.jackson.databind.JsonNode;
import net.freshservers.support.zen.domain.Comment;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateTest {

    private static final String API_ROOT = "https://bgcpos.zendesk.com/api/v2";
    private static final String credentials = "scott@freshtechnology.com/token:3eHFJWzNrxknNRiDiEaMI23nR4DZ1zvhS20AboIa ";

    @Test
    public void postTicket(){
//        UriComponentsBuilder apiUrl = UriComponentsBuilder
//                .fromUriString(API_ROOT + "/tickets")
//                .userInfo("Basic c3dhbm5pbmdlckBib2RuYXJncm91cC5jb20vdG9rZW46M2VIRkpXek5yeGtuTlJpRGlFYU1JMjNuUjREWjF6dmhTMjBBYm9JYQ==");
//
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, Object> postMap = new HashMap<>();
//        postMap.put("subject", "API Test");
//        postMap.put("comment", new Comment("This is a test"));
//        postMap.put("group_id","360000932611");
//
//
//        JsonNode jsonNode = restTemplate.postForObject(apiUrl.toUriString(), postMap, JsonNode.class);
//        System.out.println("Response");
//        System.out.println(jsonNode.toString());
    }

    @Test
    public void postTicket2(){
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, Object> postMap = new HashMap<>();
//        postMap.put("subject", "API Test");
//        postMap.put("comment", new Comment("This is a test"));
//        postMap.put("group_id","360000932611");
//
//        String uriTemplate = "https://bgcpos.zendesk.com/api/v2/tickets";
//        URI uri = UriComponentsBuilder
//                .fromUriString(uriTemplate)
//                .build().toUri();
//
//        JsonNode jsonNode = RequestEntity.post(uri)
//                .header("Authorization", "Basic c3dhbm5pbmdlckBib2RuYXJncm91cC5jb20vdG9rZW46M2VIRkpXek5yeGtuTlJpRGlFYU1JMjNuUjREWjF6dmhTMjBBYm9JYQ==")
//                .header("Content-Type","application/json")
//                .body(postMap).getClass();
//
//        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
//
//        JsonNode jsonNode = restTemplate.postForObject(requestEntity, postMap, JsonNode.class);
//        System.out.println("Response");
//        System.out.println(jsonNode.toString());
    }

}
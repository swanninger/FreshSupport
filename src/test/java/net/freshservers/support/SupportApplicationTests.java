package net.freshservers.support;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.zen.domain.Comment;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SupportApplicationTests {
    private final String apiUrl = "https://bgcpos.zendesk.com/api/v2/tickets.json";

	@Test
	public void contextLoads() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthorization("scott@freshtechnology.com/token","3eHFJWzNrxknNRiDiEaMI23nR4DZ1zvhS20AboIa")
                .build();

        Map<String,Object> ticket = new HashMap<>();
        ticket.put("subject", "API Test");
        ticket.put("comment", new Comment("This is a test"));
        ticket.put("group_id","360000932611");

        Map<String, Map<String,Object>> postMap = new HashMap<>();
        postMap.put("ticket",ticket);

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
        log.info(jsonNode.toString());
	}

}

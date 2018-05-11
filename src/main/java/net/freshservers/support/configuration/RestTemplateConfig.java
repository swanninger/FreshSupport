package net.freshservers.support.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .basicAuthorization("scott@freshtechnology.com/token","3eHFJWzNrxknNRiDiEaMI23nR4DZ1zvhS20AboIa")
                .build();
    }
}

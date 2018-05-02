package net.freshservers.support.zen.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "zen")
public class ZenApiConfiguration {

    String baseUrl;
    String ticketsUrl;
    String groupsUrl;
}

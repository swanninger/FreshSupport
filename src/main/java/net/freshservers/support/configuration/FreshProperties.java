package net.freshservers.support.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fresh")
public class FreshProperties {
    private Set<String> recipeCodes;
    private Set<String> requestTypes;
    private Set<String> positionTypes;
    private Map<String,String> systemTypes;
}

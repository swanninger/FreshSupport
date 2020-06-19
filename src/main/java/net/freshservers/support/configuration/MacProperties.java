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
@ConfigurationProperties(prefix = "mac")
public class MacProperties {
    private Set<String> macLocations;
    private Set<String> vendors;
    private Set<String> pourSizes;
    private Set<String> purchaseSizes;
    private Set<String> styles;
    private Set<String> unitSizes;
}

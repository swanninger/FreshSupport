package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MacEventCommand {
    private String storeName;
    private String contactName;
    private String contactEmail;

    private String eventDate;
    private String eventTime;
    private String eventName;
    private String eventPartners;

    private List<String> beers = new ArrayList<>();

    private String notes;
}

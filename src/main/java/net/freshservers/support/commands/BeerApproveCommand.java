package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerApproveCommand {
    private String storeName;
    private String replyToEmail;

    private String beerName;
    private String pourSize;
    private String cost;
    private String distributor;
    private String price;
}

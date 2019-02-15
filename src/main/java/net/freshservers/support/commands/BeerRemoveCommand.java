package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerRemoveCommand {
    private String storeName;
    private String contactName;
    private String contactEmail;

    private String beerName;
    private String vendor;
    private String unitSize;
    private String price;
    private String notes;
}

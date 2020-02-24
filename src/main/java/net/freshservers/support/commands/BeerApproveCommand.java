package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerApproveCommand {
    private String storeName;
    private String contactName;
    private String contactEmail;

    private String beerName;
    private String brewery;
    private String salePrice;
    private String distributor;
    private String pourSize;
    private String purchaseSize;
    private String cost;
    private String aBV;
    private String style;
    private String replacing;
    private String description;
    private String notes;
    private String calories;
    private String sugars;
    private String carbs;
    private boolean lighterSide;
}

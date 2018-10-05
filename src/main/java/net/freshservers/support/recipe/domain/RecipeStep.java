package net.freshservers.support.recipe.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RecipeStep {
    private Integer mixStep;
    private Integer mixOrder;
    private String recordType;
    private String ingredient;
    private Integer qty;
    private String measure;
    private String instructions;
}

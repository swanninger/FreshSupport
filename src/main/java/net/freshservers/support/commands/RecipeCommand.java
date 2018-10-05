package net.freshservers.support.commands;

import lombok.*;
import net.freshservers.support.recipe.domain.RecipeStep;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeCommand {

    private String userName;
    private String userPosition;
    private String userEmail;

    private String concept;
    private String location;

    private String recipeName;

    private String invUnits;
    private Integer batchSize;
    private String batchUnit;
    private String department;
    private String binStorage;
    private Integer printOrder;

    private boolean tracking;
    private boolean inCount;

    private Integer shelfLife; // hours
    private Integer unitsInCaterPack;
    private Integer unitsInPrepCont;
    private boolean printPrepLabels;

    private String QIDStorage;
    private String QIDDescription;
    private String QIDTempType; // below,above,none
    private Integer QIDTemp;

    private List<RecipeStep> recipeSteps = new ArrayList<>();
}

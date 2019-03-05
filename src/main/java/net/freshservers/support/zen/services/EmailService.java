package net.freshservers.support.zen.services;

import net.freshservers.support.commands.*;

public interface EmailService {

    void createCredentialTicket(CredentialRequestCommand command);

    void createRecipeTicket(RecipeCommand command);

    void createRefundTicket(RefundCommand command);

    void createPassResetTicket(PassResetCommand command);

    void createBeerApprovalTicket(BeerApproveCommand command);

    void createBeerRemoveTicket(BeerRemoveCommand command);

    void createMacEventTicket(MacEventCommand command);
}

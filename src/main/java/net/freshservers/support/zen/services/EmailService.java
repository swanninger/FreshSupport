package net.freshservers.support.zen.services;

import net.freshservers.support.commands.*;

public interface EmailService {

    void createCredentialTicket(CredentialRequestCommand command);

    void createRecipeTicket(RecipeCommand command);

    void createRefundTicket(RefundCommand refundCommand);

    void createPassResetTicket(PassResetCommand passResetCommand);
}

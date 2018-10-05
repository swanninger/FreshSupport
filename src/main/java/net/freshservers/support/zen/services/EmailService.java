package net.freshservers.support.zen.services;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.commands.RecipeCommand;
import net.freshservers.support.commands.TicketCommand;

public interface EmailService {

    void sendEmail(TicketCommand command);

    void sendCredentialsTicket(TicketCommand command);

    void createCredentialTicket(CredentialRequestCommand command);

    void createRecipeTicket(RecipeCommand command);
}

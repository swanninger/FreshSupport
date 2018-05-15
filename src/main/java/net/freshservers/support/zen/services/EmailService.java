package net.freshservers.support.zen.services;

import net.freshservers.support.commands.CredentialRequestCommand;

public interface EmailService {

    void sendEmail(CredentialRequestCommand command);

    void sendCredentialsTicket(CredentialRequestCommand command);
}

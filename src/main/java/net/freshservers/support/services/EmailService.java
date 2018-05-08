package net.freshservers.support.services;

import net.freshservers.support.commands.CredentialRequestCommand;

public interface EmailService {

    void sendEmail(CredentialRequestCommand command);

    void sendZenTicket(CredentialRequestCommand command);
}

package net.freshservers.support.services;

import net.freshservers.support.commands.CredentialRequestCommand;

public interface EmailService {

    Boolean sendCredentialRequest(CredentialRequestCommand command);
}

package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredentialRequestCommand {

    private String userName;
    private String concept;
    private String location;

    private String reqName;
    private String reqEmail;
    private String reqPosition;

    private String reqType;

    private String toolType;
}

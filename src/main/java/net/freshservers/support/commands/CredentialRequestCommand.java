package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.freshservers.support.domain.Position;

@Getter
@Setter
@NoArgsConstructor
public class CredentialRequestCommand {

    private String userName;
    private String concept;
    private String location;

    private String reqName;
    private String reqEmail;
    private Position reqPosition;

    private String reqType;
    private Boolean cloud;

    private String toolType;
}

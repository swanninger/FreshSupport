package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OloRequestCommand {
    String storeName;
    String contactName;
    String contactEmail;
    String requestType;
}

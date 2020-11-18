package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OloRequestCommand {
    String storeName;
    String contactName;
    String contactEmail;
    String requestType;
    String itemName;
    LocalDate dayTo68;
}

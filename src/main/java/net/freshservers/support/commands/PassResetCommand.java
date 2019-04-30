package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PassResetCommand {
    private String storeName;
    private String contactName;
    private String contactEmail;

    private String application;

    private String employeeName;
    private String employeeNumber;
    private String username;
    private String userEmail;

    private String notes;
}

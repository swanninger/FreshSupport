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
    private String replyToEmail;

    private String application;

    private String employeeName;
    private String username;


    private String submittedBy;
    private String notes;
}

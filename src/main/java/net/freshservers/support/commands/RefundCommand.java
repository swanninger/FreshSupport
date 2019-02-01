package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RefundCommand {
    private String storeName;

    private String replyToEmail;

    private String guestName;
    private String guestPhone;

    private String date;

    private String cardType;
    private Integer lastFour;

    private Long checkNumber;

    private String employeeName;
    private Integer employeeId;

    private String checkAmount;
    private String originalTip;
    private String refundAmount;

    private String explanation;

    private String notes;
}

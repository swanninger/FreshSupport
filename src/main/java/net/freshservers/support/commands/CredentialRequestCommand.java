package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.freshservers.support.domain.Position;
import net.freshservers.support.domain.SystemType;

import java.util.LinkedList;
import java.util.List;

// Takes Credential Form Data

@Getter
@Setter
@NoArgsConstructor
public class CredentialRequestCommand {

    private String userName;
    private String concept;
    private String location;
    private String userPosition;

    private String reqName;
    private String reqEmail;
    private String reqPosition;
    private String reqConcept;

    private String reqType;

    private List<String> systemTypes;

    private String forwardEmail;

//  Tools Permissions
    private List<String> empMaint;
    private List<String> hourlyRateAudit;
    private boolean invCounts;
    private boolean flash;
    private boolean salaryMgmt;
    private boolean foodBevReq;
    private boolean payrollData;

    private List<String> salesReports = new LinkedList<>();

    private String notes;
}

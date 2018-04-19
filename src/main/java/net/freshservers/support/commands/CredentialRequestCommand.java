package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.freshservers.support.domain.Position;
import net.freshservers.support.domain.SystemType;

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
    private Position reqPosition;

    private String reqType;

    private List<String> systemTypes;

    private String forwardEmail;

//  Tools Permissions
    private List<String> empMaint;
    private List<String> hourlyRateAudit;
    private Boolean invCounts;
    private Boolean flash;
    private Boolean salaryMgmt;
    private Boolean foodBevReq;
    private Boolean payrollData;

    private List<String> salesReports;
}

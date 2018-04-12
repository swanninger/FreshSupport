package net.freshservers.support.services;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.domain.SystemType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public String collectionFormatter(String title, List<String> c){
        StringBuilder sb = new StringBuilder(title + ":");
        for(String s : c){
            sb.append(" " + s + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }

    public Boolean sendCredentialRequest(CredentialRequestCommand command) {
        StringBuilder body = new StringBuilder();
        body.append("Name: " + command.getUserName() + "\n");
        body.append("Concept: " + command.getConcept() + "\n");
        body.append("Location: " + command.getLocation() + "\n");
        body.append("User Position: " + command.getUserPosition() + "\n");
        body.append("Requester: " + command.getReqName() + "\n");
        body.append("Requester Email: " + command.getReqEmail() + "\n");
        body.append("Requester Position: " + command.getReqPosition() + "\n");
        body.append("Type of Request: " + command.getReqType() + "\n");

        body.append(collectionFormatter("System Types", command.getSystemTypes()));

        if (!command.getForwardEmail().isEmpty()){
            body.append("Forward email to:" + command.getForwardEmail());
        }

        body.append("\nPermissions\n");

        if (!command.getEmpMaint().isEmpty()){
            body.append(collectionFormatter("Employee Maintenance", command.getEmpMaint()));
        }
        if (!command.getHourlyRateAudit().isEmpty()){
            body.append(collectionFormatter("Hourly Rate Audit", command.getHourlyRateAudit()));
        }
        if (command.getSalaryMgmt()){
            body.append("Salary Mgmt: Run Salary Mgmt Tool\n");
        }
        if (command.getPayrollData()){
            body.append("Payroll Data: All 3\n");
        }
        if (command.getFoodBevReq()){
            body.append("Food and Bev Request: Access Options\n");
        }
        if (command.getInvCounts()){
            body.append("Inventory Counts: View and Update Organization\n");
        }
        if (command.getFlash()){
            body.append("Flash: Can Run Flash\n");
        }
        if (!command.getSalesReports().isEmpty()){
            body.append(collectionFormatter("Sales Reports", command.getSalesReports()));
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("support@freshtechnology.com");
        message.setReplyTo(command.getReqEmail());
        message.setSubject("Credential Request Form");
        message.setText(body.toString());

        emailSender.send(message);
        return true;
    }
}

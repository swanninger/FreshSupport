package net.freshservers.support.services;

import net.freshservers.support.commands.CredentialRequestCommand;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

/* Class responsible for Sending email */
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    // Formats Lists from command data into a single formatted string
    private String collectionFormatter(String title, List<String> c){
        StringBuilder sb = new StringBuilder(title + ":");
        for(String s : c){
            sb.append(" ").append(s).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }

    //Emails credential requests
    public Boolean sendCredentialRequest(CredentialRequestCommand command) {
        StringBuilder body = new StringBuilder();
        body.append("Name: ").append(command.getUserName()).append("\n");
        body.append("Concept: ").append(command.getConcept()).append("\n");
        body.append("Location: ").append(command.getLocation()).append("\n");
        body.append("User Position: ").append(command.getUserPosition()).append("\n\n");
        body.append("Requester: ").append(command.getReqName()).append("\n");
        body.append("Requester Email: ").append(command.getReqEmail()).append("\n");
        body.append("Requester Position: ").append(command.getReqPosition()).append("\n");
        body.append("Requester Concept: ").append(command.getConcept()).append("\n\n");
        body.append("Type of Request: ").append(command.getReqType()).append("\n");
        body.append(collectionFormatter("System Types", command.getSystemTypes()));

        if (!command.getForwardEmail().isEmpty()){
            body.append("Forward email to:").append(command.getForwardEmail());
        }

        body.append("\n~Permissions~\n");

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
            body.append(collectionFormatter("Sales Reports: ", command.getSalesReports()));
        }
        if (!command.getNotes().isEmpty()){
            body.append("\nNotes: ").append(command.getNotes());
        }

        // email metadata
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("support@freshtechnology.com");
        if (command.getSystemTypes().contains("Cloud") || command.getSystemTypes().contains("Email")){
            message.setCc("angela@freshtechnology.com");
        }
        message.setReplyTo(command.getReqEmail());
        message.setSubject("Credential Request - " + command.getUserName());
        message.setText(body.toString());

        emailSender.send(message);
        return true;
    }
}

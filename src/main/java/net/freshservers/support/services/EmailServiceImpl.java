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

    /**
     *
     * @param command command object from form
     * @return message body
     */
    private String createMessageBody(CredentialRequestCommand command) {
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("Name: ").append(command.getUserName()).append("\n");
        messageBody.append("Concept: ").append(command.getConcept()).append("\n");
        messageBody.append("Location: ").append(command.getLocation()).append("\n");
        messageBody.append("User Position: ").append(command.getUserPosition()).append("\n\n");
        messageBody.append("Requester: ").append(command.getReqName()).append("\n");
        messageBody.append("Requester Email: ").append(command.getReqEmail()).append("\n");
        messageBody.append("Requester Position: ").append(command.getReqPosition()).append("\n");
        messageBody.append("Requester Concept: ").append(command.getConcept()).append("\n\n");
        messageBody.append("Type of Request: ").append(command.getReqType()).append("\n");
        messageBody.append(collectionFormatter("System Types", command.getSystemTypes()));

        if (!command.getForwardEmail().isEmpty()){
            messageBody.append("Forward email to:").append(command.getForwardEmail());
        }

        messageBody.append("\n~Permissions~\n");

        if (!command.getEmpMaint().isEmpty()){
            messageBody.append(collectionFormatter("Employee Maintenance", command.getEmpMaint()));
        }
        if (!command.getHourlyRateAudit().isEmpty()){
            messageBody.append(collectionFormatter("Hourly Rate Audit", command.getHourlyRateAudit()));
        }
        if (command.getSalaryMgmt()){
            messageBody.append("Salary Mgmt: Run Salary Mgmt Tool\n");
        }
        if (command.getPayrollData()){
            messageBody.append("Payroll Data: All 3\n");
        }
        if (command.getFoodBevReq()){
            messageBody.append("Food and Bev Request: Access Options\n");
        }
        if (command.getInvCounts()){
            messageBody.append("Inventory Counts: View and Update Organization\n");
        }
        if (command.getFlash()){
            messageBody.append("Flash: Can Run Flash\n");
        }
        if (!command.getSalesReports().isEmpty()){
            messageBody.append(collectionFormatter("Sales Reports: ", command.getSalesReports()));
        }
        if (!command.getNotes().isEmpty()){
            messageBody.append("\nNotes: ").append(command.getNotes());
        }

        return messageBody.toString();
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

    public void sendEmail(CredentialRequestCommand command){
        // email metadata
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("support@freshtechnology.com");
        if (command.getSystemTypes().contains("Cloud") || command.getSystemTypes().contains("Email")){
            message.setCc("angela@freshtechnology.com");
        }
        message.setReplyTo(command.getReqEmail());
        message.setSubject("Credential Request - " + command.getUserName());
        message.setText(createMessageBody(command));

        emailSender.send(message);
    }

    @Override
    public void sendZenTicket(CredentialRequestCommand command) {
        String messageBody = createMessageBody(command);




    }

}

package net.freshservers.support.zen.services;

import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.commands.RecipeCommand;
import net.freshservers.support.commands.TicketCommand;
import net.freshservers.support.recipe.domain.RecipeStep;
import net.freshservers.support.zen.domain.Comment;
import net.freshservers.support.zen.domain.Requester;
import net.freshservers.support.zen.domain.Ticket;
import net.freshservers.support.zen.domain.TicketField;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Class responsible for Sending email */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private final ZenApiService zenApiService;

    public EmailServiceImpl(JavaMailSender emailSender, ZenApiService zenApiService) {
        this.emailSender = emailSender;
        this.zenApiService = zenApiService;
    }

    /**
     * @param command command object from form
     * @return message body
     */
    @Override
    public void createCredentialTicket(CredentialRequestCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder messageBody = new StringBuilder();
        messageBody.append("Name: ").append(command.getUserName()).append("\n");
        messageBody.append("Concept: ").append(command.getConcept()).append("\n");
        messageBody.append("Location: ").append(command.getLocation()).append("\n");
        messageBody.append("User Position: ").append(command.getUserPosition()).append("\n\n");
        messageBody.append("Requester: ").append(command.getReqName()).append("\n");
        if (command.getReqEmail() != null) {
            messageBody.append("Requester Email: ").append(command.getReqEmail()).append("\n");
        } else {
            messageBody.append("Requester Email: blank");
        }
        messageBody.append("Requester Position: ").append(command.getReqPosition()).append("\n");
        messageBody.append("Requester Concept: ").append(command.getReqConcept()).append("\n\n");
        messageBody.append("Type of Request: ").append(command.getReqType()).append("\n");
        messageBody.append(collectionFormatter("System Types", command.getSystemTypes()));

        if (!command.getForwardEmail().isEmpty()) {
            messageBody.append("Forward email to:").append(command.getForwardEmail());
        }

        messageBody.append("\n~Permissions~\n");

        if (!command.getEmpMaint().isEmpty()) {
            messageBody.append(collectionFormatter("Employee Maintenance", command.getEmpMaint()));
        }
        if (!command.getHourlyRateAudit().isEmpty()) {
            messageBody.append(collectionFormatter("Hourly Rate Audit", command.getHourlyRateAudit()));
        }
        if (command.isSalaryMgmt()) {
            messageBody.append("Salary Mgmt: Run Salary Mgmt Tool\n");
        }
        if (command.isPayrollData()) {
            messageBody.append("Payroll Data: All 3\n");
        }
        if (command.isFoodBevReq()) {
            messageBody.append("Food and Bev Request: Access Options\n");
        }
        if (command.isInvCounts()) {
            messageBody.append("Inventory Counts: View and Update Organization\n");
        }
        if (command.isFlash()) {
            messageBody.append("Flash: Can Run Flash\n");
        }
        if (!command.getSalesReports().isEmpty()) {
            messageBody.append(collectionFormatter("Sales Reports: ", command.getSalesReports()));
        }
        if (!command.getNotes().isEmpty()) {
            messageBody.append("\nNotes: ").append(command.getNotes());
        }

        ticketCommand.setBody(messageBody.toString());
        ticketCommand.setRequesterName(command.getReqName());
        ticketCommand.setRequesterEmail(command.getReqEmail());
        ticketCommand.setConcept(command.getConcept());
        ticketCommand.setSubject("Credential Request - " + command.getUserName());
        if (command.getSystemTypes().contains("Cloud") || command.getSystemTypes().contains("Email")){
            ticketCommand.getCcs().add("angela@freshtechnology.com");
        }
        ticketCommand.setGroup(360000932611L);

        sendCredentialsTicket(ticketCommand);
    }

    @Override
    public void createRecipeTicket(RecipeCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("From: ").append(command.getUserName()).append("\n");
        body.append("Position: ").append(command.getUserPosition()).append("\n");
        body.append("Location: ").append(command.getConcept()).append(" - ").append(command.getLocation()).append("\n");

        body.append("\nRecipe Info:\n");
        body.append("Recipe Name: ").append(command.getRecipeName()).append("\n");
        body.append("Inventory Unit: ").append(command.getInvUnits()).append("\n");
        body.append("Batch Size: ").append(command.getBatchSize()).append("\n");
        body.append("Batch Unit: ").append(command.getBatchUnit()).append("\n");
        body.append("Department: ").append(command.getDepartment()).append("\n");
        body.append("Bin Storage: ").append(command.getBinStorage()).append("\n");
        body.append("Print Order: ").append(command.getPrintOrder()).append("\n");
        body.append("Shelf Life(Hrs): ").append(command.getShelfLife()).append("\n");
        body.append("Units In Cater Pack: ").append(command.getUnitsInCaterPack()).append("\n");

        body.append("Tracking: ").append(command.isTracking()).append("\n");
        body.append("In Count: ").append(command.isInCount()).append("\n");
        body.append("Print Prep Labels: ").append(command.isPrintPrepLabels()).append("\n");

        body.append("\nQID Info:\n");
        body.append("QID Storage: ").append(command.getQIDStorage()).append("\n");
        body.append("QID Temp Type: ").append(command.getQIDTempType()).append("\n");
        body.append("QID Temp: ").append(command.getQIDTemp()).append("\n");
        body.append("QID Description: ").append(command.getQIDDescription()).append("\n");

        body.append("\nRecipe Steps:\n");
        for (RecipeStep step : command.getRecipeSteps()) {
            body.append(step.toString()).append("\n");
        }

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getUserName());
        ticketCommand.setRequesterEmail(command.getUserEmail());
        ticketCommand.setConcept(command.getConcept());
        ticketCommand.setSubject("Recipe Request");
        ticketCommand.setGroup(360002668331L);

        sendCredentialsTicket(ticketCommand);
    }

    // Formats Lists from command data into a single formatted string
    private String collectionFormatter(String title, List<String> c) {
        StringBuilder sb = new StringBuilder(title + ":");
        for (String s : c) {
            sb.append(" ").append(s).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }

    public void sendEmail(TicketCommand command) {
        // email metadata
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("support@freshtechnology.com");

        if (!command.getCcs().isEmpty()) {
            String[] ccs = new String[command.getCcs().size()];
            message.setCc(command.getCcs().toArray(ccs));
        }

        message.setReplyTo(command.getRequesterEmail());
        message.setSubject(command.getSubject());
        message.setText(command.getBody());

        emailSender.send(message);
    }

    /**
     * Submit the ticket through Zendesk
     *
     * If fails, send through email
     *
     * @param command
     */
    @Override
    public void sendCredentialsTicket(TicketCommand command) {

        Ticket ticket = new Ticket(command.getSubject(), new Comment(command.getBody()), command.getGroup());
        ticket.setRequester(new Requester(command.getRequesterName(), command.getRequesterEmail()));

        List<TicketField> fields = new ArrayList<>();
        fields.add(new TicketField(25140303, command.getConcept().toUpperCase())); // set concept
        ticket.setCustom_fields(fields);

        ticket.setCollaborators(command.getCcs());

        try {
            zenApiService.sendTicket(ticket); //try sending through ZenDesk API
        } catch (HttpClientErrorException e) {
            sendEmail(command); //if fails, send through email
            log.error(e + "\n Failed ticket:\n" + ticket);
        }
    }

}

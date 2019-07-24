package net.freshservers.support.zen.services;

import lombok.extern.slf4j.Slf4j;
import net.freshservers.support.commands.*;
import net.freshservers.support.recipe.domain.RecipeStep;
import net.freshservers.support.zen.domain.Comment;
import net.freshservers.support.zen.domain.Requester;
import net.freshservers.support.zen.domain.Ticket;
import net.freshservers.support.zen.domain.TicketField;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        messageBody.append("Email: ").append(command.getUserEmail()).append("\n");
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
        ticketCommand.getCcs().add("adzara@freshtechnology.com");
        ticketCommand.setGroup(360000932611L);

        sendZenTicket(ticketCommand);
    }

    private String prettyBoolean(boolean b) {
        if(b){
            return "yes";
        }else {
            return "no";
        }
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

        body.append("Tracking: ").append(prettyBoolean(command.isTracking())).append("\n");
        body.append("In Count: ").append(prettyBoolean(command.isInCount())).append("\n");
        body.append("Print Prep Labels: ").append(prettyBoolean(command.isPrintPrepLabels())).append("\n");

        body.append("\nQID Info:\n");
        body.append("QID Storage: ").append(command.getQIDStorage()).append("\n");
        body.append("QID Temp Type: ").append(command.getQIDTempType()).append("\n");
        body.append("QID Temp: ").append(command.getQIDTemp()).append("\n");
        body.append("QID Description: ").append(command.getQIDDescription()).append("\n");

        body.append("\nRecipe Steps:\n");
        for (RecipeStep step : command.getRecipeSteps()) {
            body.append("Step: ").append(step.getMixStep()).append(" ");
            body.append("Order: ").append(step.getMixOrder()).append(" ");
            body.append("Type: ").append(step.getRecordType()).append(" ");
            if (!step.getIngredient().isEmpty()) {
                body.append("Ingredient: ").append(step.getIngredient()).append(" ");
            }
            if (step.getQty() != null) {
                body.append("Qty: ").append(step.getQty()).append(" ");
            }
            if (!step.getMeasure().isEmpty()) {
                body.append("Measure: ").append(step.getMeasure()).append(" ");
            }
            if (!step.getInstructions().isEmpty()) {
                body.append("Instructions: ").append(step.getInstructions()).append(" ");
            }
            body.append("\n");
        }

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getUserName());
        ticketCommand.setRequesterEmail(command.getUserEmail());
        ticketCommand.setConcept(command.getConcept());
        ticketCommand.setSubject("Recipe Request");
        ticketCommand.setGroup(360002668331L);

        sendZenTicket(ticketCommand);
    }

    @Override
    public void createRefundTicket(RefundCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("Location: ").append(command.getStoreName()).append("\n");
        body.append("Contact Name: ").append(command.getContactName()).append("\n");
        body.append("Guest Name: ").append(command.getGuestName()).append("\n");
        body.append("Guest Phone: ").append(command.getGuestPhone()).append("\n");
        body.append("Date of Transaction: ").append(command.getDate()).append("\n");
        body.append("Card Type: ").append(command.getCardType()).append("\n");
        body.append("Last 4 Digits: ").append(command.getLastFour()).append("\n");
        body.append("Check Number: ").append(command.getCheckNumber()).append("\n");
        body.append("Employee Name: ").append(command.getEmployeeName()).append("\n");
        body.append("Employee Id: ").append(command.getEmployeeId()).append("\n");
        body.append("Check Amount: ").append(command.getCheckAmount()).append("\n");
        body.append("Original Tip: ").append(command.getOriginalTip()).append("\n");
        body.append("Refund Amount: ").append(command.getRefundAmount()).append("\n");
        body.append("Reason for Refund: ").append(command.getExplanation()).append("\n").append("\n");
        body.append("Notes: ").append(command.getNotes()).append("\n");

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getStoreName());
        ticketCommand.setRequesterEmail(command.getContactEmail());
        ticketCommand.setConcept("MAC");
        ticketCommand.setSubject("MAC - EDC Refund Request");
        ticketCommand.setGroup(360003466432L);

        sendZenTicket(ticketCommand);
    }

    @Override
    public void createPassResetTicket(PassResetCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("Location: ").append(command.getStoreName()).append("\n");
        body.append("Contact Name: ").append(command.getContactName()).append("\n");
        body.append("Application: ").append(command.getApplication()).append("\n");
        body.append("Employee Name: ").append(command.getEmployeeName()).append("\n");
        body.append("Username: ").append(command.getUsername()).append("\n");
        body.append("User Email: ").append(command.getUserEmail()).append("\n").append("\n");
        body.append("Notes: ").append(command.getNotes()).append("\n");

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getStoreName());
        ticketCommand.setRequesterEmail(command.getContactEmail());
        ticketCommand.setConcept("MAC");
        ticketCommand.setSubject("MAC - Password Reset Request");
        ticketCommand.setGroup(360003466432L);

        sendZenTicket(ticketCommand);
    }

    @Override
    public void createBeerApprovalTicket(BeerApproveCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("Location: ").append(command.getStoreName()).append("\n");
        body.append("Contact Name: ").append(command.getContactName()).append("\n");
        body.append("Beer Name: ").append(command.getBeerName()).append("\n");
        body.append("Brewery: ").append(command.getBrewery()).append("\n");
        body.append("Distributor: ").append(command.getDistributor()).append("\n");
        body.append("Price: ").append(command.getSalePrice()).append("\n").append("\n");
        body.append("Pour Size: ").append(command.getPourSize()).append("\n");
        body.append("Purchase Size: ").append(command.getPurchaseSize()).append("\n");
        body.append("Cost: ").append(command.getCost()).append("\n");
        body.append("ABV %: ").append(command.getABV()).append("\n");
        body.append("Style: ").append(command.getStyle()).append("\n");
        body.append("Replacing?: ").append(command.getReplacing()).append("\n");
        body.append("Brewniversity Description: ").append(command.getDescription()).append("\n");
        body.append("Notes: ").append(command.getNotes()).append("\n");

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getStoreName());
        ticketCommand.setRequesterEmail(command.getContactEmail());
        ticketCommand.setConcept("MAC");
        ticketCommand.setSubject("MAC - Beer Approval Request");
        ticketCommand.setGroup(360004006551L);

        sendZenTicket(ticketCommand);
    }

    @Override
    public void createBeerRemoveTicket(BeerRemoveCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("Location: ").append(command.getStoreName()).append("\n");
        body.append("Contact Name: ").append(command.getContactName()).append("\n");
        body.append("Beer Name: ").append(command.getBeerName()).append("\n");
        body.append("Vendor: ").append(command.getVendor()).append("\n");
        body.append("Unit Size: ").append(command.getUnitSize()).append("\n");
        body.append("Price: ").append(command.getPrice()).append("\n").append("\n");
        body.append("Notes: ").append(command.getNotes()).append("\n");

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getStoreName());
        ticketCommand.setRequesterEmail(command.getContactEmail());
        ticketCommand.setConcept("MAC");
        ticketCommand.setSubject("MAC - Beer Remove Exclude");
        ticketCommand.setGroup(360004006551L);

        sendZenTicket(ticketCommand);
    }

    @Override
    public void createMacEventTicket(MacEventCommand command) {
        TicketCommand ticketCommand = new TicketCommand();

        StringBuilder body = new StringBuilder();
        body.append("Location: ").append(command.getStoreName()).append("\n");
        body.append("Contact Name: ").append(command.getContactName()).append("\n");
        body.append("Event Date: ").append(command.getEventDate()).append("\n");
        body.append("Event Time: ").append(command.getEventTime()).append("\n");
        body.append("Event Name: ").append(command.getEventName()).append("\n");
        body.append("Event Partners: ").append(command.getEventPartners()).append("\n").append("\n");
        body.append("Beers: ").append("\n");
        for (String beer: command.getBeers()){
            body.append(beer).append("\n");
        }
        body.append("\n").append("Notes: ").append(command.getNotes()).append("\n");

        ticketCommand.setBody(body.toString());
        ticketCommand.setRequesterName(command.getStoreName());
        ticketCommand.setRequesterEmail(command.getContactEmail());
        ticketCommand.setConcept("MAC");
        ticketCommand.setSubject("MAC - Event");
        ticketCommand.setGroup(23199149L);

        sendZenTicket(ticketCommand);
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

    private void sendEmail(TicketCommand command) {
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
    private void sendZenTicket(TicketCommand command) {

        Ticket ticket = new Ticket(command.getSubject(), new Comment(command.getBody()), command.getGroup());
        ticket.setRequester(new Requester(command.getRequesterName(), command.getRequesterEmail()));

        List<TicketField> fields = new ArrayList<>();
        fields.add(new TicketField(25140303, command.getConcept().toUpperCase())); // set concept
        ticket.setCustom_fields(fields);

        ticket.setCollaborators(command.getCcs());

        try {
            zenApiService.sendTicket(ticket); //try sending through ZenDesk API
        } catch (Exception e) {
            try {
                zenApiService.sendTicket(ticket); //try again
            } catch (Exception e1) {
                sendEmail(command); //if fails, send through email
                log.error(e + "\n Failed ticket:\n" + ticket);
            }
        }
    }

}

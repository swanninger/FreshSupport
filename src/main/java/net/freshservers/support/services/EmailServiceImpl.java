package net.freshservers.support.services;

import net.freshservers.support.commands.CredentialRequestCommand;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public Boolean sendCredentialRequest(CredentialRequestCommand command) {
        StringBuilder body = new StringBuilder();
        body.append("Name: " + command.getUserName() + "\n");
        body.append("Concept: " + command.getConcept() + "\n");
        body.append("Location: " + command.getLocation() + "\n");
        body.append("Requester: " + command.getReqName() + "\n");
        body.append("Requester Email: " + command.getReqEmail() + "\n");
        body.append("Requester Position: " + command.getReqPosition() + "\n");
        body.append("Type of Request: " + command.getReqType() + "\n");
        if (command.getCloud()) {
            body.append("Cloud: " + command.getCloud() + "\n");
        }
//        body.append(command. + "\n");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("scott@freshtechnology.com");
        message.setSubject("FormTest");
        message.setText(body.toString());

        emailSender.send(message);
        return true;
    }
}

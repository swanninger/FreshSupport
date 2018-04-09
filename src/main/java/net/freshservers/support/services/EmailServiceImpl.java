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

    public Boolean sendCredentialRequest(CredentialRequestCommand command){
        String text = new String("Concept: " + command.getConcept() + "\n UserName: " + command.getUserName());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("scott@freshtechnology.com");
        message.setSubject("FormTest");
        message.setText(text);

        emailSender.send(message);
        return true;
    }
}

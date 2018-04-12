package net.freshservers.support.controllers;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.domain.Position;
import net.freshservers.support.domain.RequestType;
import net.freshservers.support.domain.SystemType;
import net.freshservers.support.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// Class for controlling Credential Form pages
@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/credentialForm")
    public String getRequestForm(Model model){
        model.addAttribute("credentialRequest", new CredentialRequestCommand());
        model.addAttribute("allPositions", Position.values());
        model.addAttribute("requestTypes", RequestType.values());
        model.addAttribute("systemTypes", SystemType.values());
        return "credentialForm";
    }

    @PostMapping("email")
    public String sendEmail(@ModelAttribute("credentialForm") CredentialRequestCommand command){
        emailService.sendCredentialRequest(command);
        return "redirect:/thanks";
    }

    @RequestMapping("/emailtest")
    public String emailTest(){
        CredentialRequestCommand command = new CredentialRequestCommand();
        command.setConcept("TAZ");
        command.setUserName("Joe");
        emailService.sendCredentialRequest(command);

        return "index";
    }

    @RequestMapping("/thanks")
    public String thanks(){
        return "thanks";
    }
}

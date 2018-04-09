package net.freshservers.support.controllers;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/credentialForm")
    public String getRequestForm(Model model){
        model.addAttribute("credentialForm", new CredentialRequestCommand());
        return "credentialForm";
    }

    @PostMapping("email")
    public String sendEmail(@ModelAttribute("email") CredentialRequestCommand command){
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
}

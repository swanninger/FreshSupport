package net.freshservers.support.controllers;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.domain.Position;
import net.freshservers.support.domain.RequestType;
import net.freshservers.support.domain.SystemType;
import net.freshservers.support.zen.services.EmailService;
import net.freshservers.support.services.UserDetailsImpl;
import net.freshservers.support.services.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Class for controlling Credential Form pages
@Controller
public class EmailController {

    private final EmailService emailService;
    private final UserServiceImpl userService;

    public EmailController(EmailService emailService, UserServiceImpl userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @RequestMapping("/credentialform")
    public String getRequestForm(Model model){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute(user);
        model.addAttribute("credentialRequest", new CredentialRequestCommand());
        model.addAttribute("allPositions", Position.values());
        model.addAttribute("requestTypes", RequestType.values());
        model.addAttribute("systemTypes", SystemType.values());
        return "credentialForm";
    }

    @RequestMapping("/formTest")
    public String getTestForm(Model model){
        UserDetailsImpl userDetail = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute(userDetail);
        model.addAttribute("credentialRequest", new CredentialRequestCommand());
        model.addAttribute("allPositions", Position.values());
        model.addAttribute("requestTypes", RequestType.values());
        model.addAttribute("systemTypes", SystemType.values());
        return "formTest";
    }

    @PostMapping("/email")
    public String sendEmail(@ModelAttribute("credentialForm") CredentialRequestCommand command){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        command.setReqName(user.getFirstName() + " " + user.getLastName());
        command.setReqPosition(user.getTypeUser());
//        if (command.getReqEmail().isEmpty()){
//            command.setReqEmail(user.getEmail());
//        }
        command.setConcept(user.getConcept());
        emailService.sendCredentialsTicket(command);
        return "redirect:/thanks";
    }

    @RequestMapping("/thanks")
    public String thanks(){
        return "thanks";
    }
}

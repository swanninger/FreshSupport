package net.freshservers.support.controllers;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.configuration.FreshProperties;
import net.freshservers.support.domain.User;
import net.freshservers.support.zen.services.EmailService;
import net.freshservers.support.services.UserDetailsImpl;
import net.freshservers.support.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
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
    private final FreshProperties freshProperties;

    public EmailController(EmailService emailService, UserServiceImpl userService, FreshProperties freshProperties) {
        this.emailService = emailService;
        this.userService = userService;
        this.freshProperties = freshProperties;
    }

    @RequestMapping("/credentialform")
    public String getRequestForm(Model model, Authentication authentication){
        UserDetailsImpl userDetail = (UserDetailsImpl)authentication.getPrincipal();
        User currentUser = userDetail.getUser();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("stores", userService.getStoresNames(currentUser));
        model.addAttribute("concepts", userService.getAllConceptCodes(currentUser));
        model.addAttribute("credentialRequest", new CredentialRequestCommand());
        model.addAttribute("allPositions", freshProperties.getPositionTypes());
        model.addAttribute("requestTypes", freshProperties.getRequestTypes());
        model.addAttribute("systemTypes", freshProperties.getSystemTypes());

        return "zen/credentialForm";
    }

    @RequestMapping("/formTest")
    public String getTestForm(){

        return "zen/formTest";
    }

    @PostMapping("/email")
    public String sendCredentialRequest(@ModelAttribute("credentialForm") CredentialRequestCommand command){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        command.setReqName(user.getFirstName() + " " + user.getLastName());
        command.setReqPosition(user.getTypeUser());
        if (command.getReqEmail().isEmpty()){
            command.setReqEmail(user.getEmail());
        }
        command.setConcept(user.getConcept());
        emailService.sendCredentialsTicket(command);
        return "redirect:/thanks";
    }

    @RequestMapping("/thanks")
    public String thanks(){
        return "zen/thanks";
    }
}

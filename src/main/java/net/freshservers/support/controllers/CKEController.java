package net.freshservers.support.controllers;

import net.freshservers.support.commands.Tier2Command;
import net.freshservers.support.configuration.CKEProperties;
import net.freshservers.support.services.UserServiceImpl;
import net.freshservers.support.zen.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cke")
public class CKEController {
    private final EmailService emailService;
    private final UserServiceImpl userService;
    private final CKEProperties ckeProperties;

    public CKEController(EmailService emailService, UserServiceImpl userService, CKEProperties ckeProperties) {
        this.emailService = emailService;
        this.userService = userService;
        this.ckeProperties = ckeProperties;
    }

    @GetMapping("/tier2")
    public String getTier2Form(Model model) {
        model.addAttribute("tier2request", new Tier2Command());

        return "cke/tier2";
    }

    @PostMapping("/tier2/email")
    public String sendTier2Request(@ModelAttribute("tier2request") Tier2Command tier2Command) {
        emailService.createCkeTier2Ticket(tier2Command);
        return "redirect:/thanks";
    }
}

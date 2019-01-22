package net.freshservers.support.controllers;

import net.freshservers.support.commands.PassResetCommand;
import net.freshservers.support.commands.RefundCommand;
import net.freshservers.support.zen.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MacController {
    private final EmailService emailService;

    public MacController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/mac/refund")
    public String getEdcRefund(Model model) {
        model.addAttribute("refundRequest", new RefundCommand());
        return "mac/edcRefund";
    }

    @PostMapping("/mac/refundEmail")
    public String sendRefundRequest(@ModelAttribute("refundRequest") RefundCommand refundCommand) {

        return null;
    }

    @GetMapping("/mac/reset")
    public String getPassReset(Model model) {
        model.addAttribute("passResetRequest", new PassResetCommand());
        return "mac/passReset";
    }
}

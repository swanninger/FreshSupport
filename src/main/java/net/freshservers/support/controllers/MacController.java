package net.freshservers.support.controllers;

import net.freshservers.support.commands.BeerApproveCommand;
import net.freshservers.support.commands.BeerRemoveCommand;
import net.freshservers.support.commands.PassResetCommand;
import net.freshservers.support.commands.RefundCommand;
import net.freshservers.support.configuration.FreshProperties;
import net.freshservers.support.zen.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MacController {
    private final EmailService emailService;
    private final FreshProperties freshProperties;

    public MacController(EmailService emailService, FreshProperties freshProperties) {
        this.emailService = emailService;
        this.freshProperties = freshProperties;
    }

    @GetMapping("/mac/refund")
    public String getEdcRefund(Model model) {
        model.addAttribute("refundRequest", new RefundCommand());
        model.addAttribute("locations", freshProperties.getMacLocations());
        return "mac/edcRefund";
    }

    @PostMapping("/mac/refundEmail")
    public String sendRefundRequest(@ModelAttribute("refundRequest") RefundCommand command) {
        emailService.createRefundTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/reset")
    public String getPassReset(Model model) {
        model.addAttribute("passResetRequest", new PassResetCommand());
        model.addAttribute("locations", freshProperties.getMacLocations());
        return "mac/passReset";
    }

    @PostMapping("/mac/resetEmail")
    public String sendPassResetRequest(@ModelAttribute("passResetRequest") PassResetCommand command) {
        emailService.createPassResetTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/beer/approve")
    public String getBeerApprove(Model model) {
        model.addAttribute("beerApprove", new BeerApproveCommand());
        model.addAttribute("locations", freshProperties.getMacLocations());
        return "mac/beerApproval";
    }

    @PostMapping("/mac/beer/approveEmail")
    public String sendBeerApprove(@ModelAttribute("passResetRequest") BeerApproveCommand command) {
        emailService.createBeerApprovalTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/beer/remove")
    public String getBeerRemove(Model model) {
        model.addAttribute("beerRemove", new BeerRemoveCommand());
        model.addAttribute("locations", freshProperties.getMacLocations());
        return "mac/beerRemove";
    }

    @PostMapping("/mac/beer/removeEmail")
    public String sendBeerRemove(@ModelAttribute("passResetRequest") BeerRemoveCommand command) {
        emailService.createBeerRemoveTicket(command);
        return "redirect:/thanks";
    }
}

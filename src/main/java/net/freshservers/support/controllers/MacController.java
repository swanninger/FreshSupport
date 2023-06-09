package net.freshservers.support.controllers;

import net.freshservers.support.commands.*;
import net.freshservers.support.configuration.FreshProperties;
import net.freshservers.support.configuration.MacProperties;
import net.freshservers.support.zen.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class MacController {
    private final EmailService emailService;
    private final MacProperties macProperties;

    public MacController(EmailService emailService, MacProperties macProperties) {
        this.emailService = emailService;
        this.macProperties = macProperties;
    }

    @GetMapping("/mac/refund")
    public String getEdcRefund(Model model) {
        model.addAttribute("refundRequest", new RefundCommand());
        model.addAttribute("locations", macProperties.getMacLocations());
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
        model.addAttribute("locations", macProperties.getMacLocations());
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
        model.addAttribute("locations", macProperties.getMacLocations());
        model.addAttribute("pourSizes", macProperties.getPourSizes());
        model.addAttribute("purchaseSizes", macProperties.getPurchaseSizes());
        model.addAttribute("styles", macProperties.getStyles());
        return "mac/beerApproval";
    }

    @PostMapping("/mac/beer/approveEmail")
    public String sendBeerApprove(@ModelAttribute("passResetRequest") BeerApproveCommand command) {
        emailService.createBeerApprovalTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/beer/remove")
    public String getBeerRemove(Model model) {
        Set<String> unitSizes = macProperties.getPurchaseSizes();
        for (String unitSize : unitSizes) {
            if (unitSize.startsWith("c")) {
                unitSizes.remove(unitSize);
            }
        }
        model.addAttribute("beerRemove", new BeerRemoveCommand());
        model.addAttribute("locations", macProperties.getMacLocations());
        model.addAttribute("vendors", macProperties.getVendors());
        model.addAttribute("unitSizes", unitSizes);

        return "mac/beerRemove";
    }

    @PostMapping("/mac/beer/removeEmail")
    public String sendBeerRemove(@ModelAttribute("beerRemove") BeerRemoveCommand command) {
        emailService.createBeerRemoveTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/event")
    public String getEvent(Model model) {
        model.addAttribute("event", new MacEventCommand());
        model.addAttribute("locations", macProperties.getMacLocations());
        return "mac/event";
    }

    @PostMapping("/mac/eventEmail")
    public String sendEvent(@ModelAttribute("event") MacEventCommand command) {
        emailService.createMacEventTicket(command);
        return "redirect:/thanks";
    }

    @GetMapping("/mac/olo")
    public String getOloRequest(Model model) {
        model.addAttribute("oloRequest", new OloRequestCommand());
        model.addAttribute("locations", macProperties.getMacLocations());

        return "mac/olo";
    }

    @PostMapping("/mac/oloEmail")
    public String sendOloRequest(@ModelAttribute("oloRequest") OloRequestCommand command) {

        return "redirect:/thanks";
    }
}

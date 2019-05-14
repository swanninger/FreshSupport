package net.freshservers.support.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tech")
public class TechController {

    @GetMapping("/zenLinks")
    public String getZenLinks() {
        return "tech/zenLinks";
    }
}

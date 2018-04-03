package net.freshservers.support.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

    public EmailController() {
    }

    @RequestMapping("/requestform")
    public String getRequestForm(){

        return "requestform";
    }
}

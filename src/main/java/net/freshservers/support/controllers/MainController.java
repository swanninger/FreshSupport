package net.freshservers.support.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"index","/"})
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }
}
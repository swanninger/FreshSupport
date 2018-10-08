package net.freshservers.support.controllers;

import net.freshservers.support.commands.CredentialRequestCommand;
import net.freshservers.support.commands.RecipeCommand;
import net.freshservers.support.configuration.FreshProperties;
import net.freshservers.support.domain.User;
import net.freshservers.support.recipe.domain.RecipeStep;
import net.freshservers.support.zen.services.EmailService;
import net.freshservers.support.services.UserDetailsImpl;
import net.freshservers.support.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping({"/credentialform","/form/credential"})
    public String getRequestForm(Model model, Authentication authentication){
        UserDetailsImpl userDetail = (UserDetailsImpl)authentication.getPrincipal();
        User user = userDetail.getUser();

        model.addAttribute("user", user);
        model.addAttribute("stores", userService.getStoresNames(user));
        model.addAttribute("concepts", userService.getAllConceptCodes(user));
        model.addAttribute("credentialRequest", new CredentialRequestCommand());
        model.addAttribute("allPositions", freshProperties.getPositionTypes());
        model.addAttribute("requestTypes", freshProperties.getRequestTypes());
        model.addAttribute("systemTypes", freshProperties.getSystemTypes());

        return "zen/credentialForm";
    }

    @PostMapping("/credentialEmail")
    public String sendCredentialRequest(@ModelAttribute("credentialRequest") CredentialRequestCommand command){
        emailService.createCredentialTicket(command);
        return "redirect:/thanks";
    }

    @RequestMapping({"/recipeform","/form/recipe"})
    public String getRecipeForm(RecipeCommand command, Model model, Authentication authentication){
        UserDetailsImpl userDetail = (UserDetailsImpl)authentication.getPrincipal();
        User user = userDetail.getUser();

        command.getRecipeSteps().add(new RecipeStep());

        model.addAttribute("user", user);
        model.addAttribute("stores", userService.getStoresNames(user));
        model.addAttribute("concepts", userService.getAllConceptCodes(user));
        model.addAttribute("recipeRequest", command);


        return "zen/recipeForm";
    }

    @PostMapping("/recipeEmail" )
    public String sendRecipeRequest(@ModelAttribute("recipeRequest") RecipeCommand command){
        emailService.createRecipeTicket(command);
        return "redirect:/thanks";
    }

    @RequestMapping("/thanks")
    public String thanks(){
        return "zen/thanks";
    }
}

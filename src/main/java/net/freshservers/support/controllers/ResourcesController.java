package net.freshservers.support.controllers;

import net.freshservers.support.configuration.FreshProperties;
import net.freshservers.support.domain.User;
import net.freshservers.support.services.AppService;
import net.freshservers.support.services.UserDetailsImpl;
import net.freshservers.support.services.UserServiceImpl;
import net.freshservers.support.services.WallpaperService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
    private final WallpaperService wallpaperService;
    private final AppService appService;
    private final UserServiceImpl userService;
    private final FreshProperties freshProperties;

    public ResourcesController(WallpaperService wallpaperService, AppService appService, UserServiceImpl userService, FreshProperties freshProperties) {
        this.wallpaperService = wallpaperService;
        this.appService = appService;
        this.userService = userService;
        this.freshProperties = freshProperties;
    }

    @RequestMapping("/wallpaper")
    public String getWallpapers(Model model){
        model.addAttribute("ipadWallpapers", wallpaperService.getIpadWallpapers());
        model.addAttribute("androidWallpapers", wallpaperService.getAndroidWallpapers());
        return "resources/wallpaper";
    }
    @RequestMapping("/apps")
    public String getApps(Model model){
        model.addAttribute("ipadApps", appService.getIpadApps());
        model.addAttribute("androidApps", appService.getAndroidApps());
        model.addAttribute("techApps", appService.getTechApps());
        return "resources/apps";
    }

    @RequestMapping("/recipelinks")
    public String getRecipes(Model model, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        User currentUser = userDetails.getUser();

        Set<String> concepts = userService.getAllConceptCodes(currentUser);
        concepts.retainAll(freshProperties.getRecipeCodes());

        model.addAttribute("concepts", concepts);
        return "resources/recipelinks";
    }
}

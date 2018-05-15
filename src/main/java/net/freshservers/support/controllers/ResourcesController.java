package net.freshservers.support.controllers;

import net.freshservers.support.domain.RConcepts;
import net.freshservers.support.services.AppService;
import net.freshservers.support.services.WallpaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
    private final WallpaperService wallpaperService;
    private final AppService appService;

    public ResourcesController(WallpaperService wallpaperService, AppService appService) {
        this.wallpaperService = wallpaperService;
        this.appService = appService;
    }

    @RequestMapping("/wallpaper")
    public String getWallpapers(Model model){
        model.addAttribute("ipadWallpapers", wallpaperService.getIpadWallpapers());
        model.addAttribute("androidWallpapers", wallpaperService.getAndroidWallpapers());
        return "wallpaper";
    }
    @RequestMapping("/apps")
    public String getApps(Model model){
        model.addAttribute("ipadApps", appService.getIpadApps());
        model.addAttribute("androidApps", appService.getAndroidApps());
        return "apps";
    }

    @RequestMapping("/recipelinks")
    public String getRecipes(Model model){
        model.addAttribute("concepts", RConcepts.values());
        return "recipelinks";
    }
}

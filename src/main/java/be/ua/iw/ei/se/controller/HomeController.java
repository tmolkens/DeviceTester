package be.ua.iw.ei.se.controller;

import be.ua.iw.ei.se.model.User;
import be.ua.iw.ei.se.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/","/home"})
    @PreAuthorize("hasRole('logon')")
    public String showHomepage(final ModelMap model){
        model.addAttribute("user", new User("",""));
        return "homepage";
    }
}





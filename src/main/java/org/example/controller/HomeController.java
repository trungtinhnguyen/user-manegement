package org.example.controller;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping (value = "/login")
    public ModelAndView loginPage () {
        return new ModelAndView("login");
    }

    @GetMapping (value = "/home")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }
    @GetMapping (value = "/dashboard")
    public ModelAndView dashboard () {
        return new ModelAndView("dashboard");
    }

    @GetMapping (value = "/access-denied")
    public ModelAndView accessDenied () {
        return new ModelAndView("access-denied");
    }
}

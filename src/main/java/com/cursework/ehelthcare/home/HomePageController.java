package com.cursework.ehelthcare.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/account/user")
    public String getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return "user-home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}

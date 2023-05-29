package com.cursework.ehelthcare.home;

import com.cursework.ehelthcare.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }



}

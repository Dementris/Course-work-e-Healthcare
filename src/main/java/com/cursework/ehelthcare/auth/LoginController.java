package com.cursework.ehelthcare.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, AuthenticationRequest request){
        model.addAttribute("login-request", request);
        return "login";
    }

}

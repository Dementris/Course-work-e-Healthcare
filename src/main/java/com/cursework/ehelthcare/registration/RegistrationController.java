package com.cursework.ehelthcare.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public String getForm(Model model, RegistrationRequest request){
        model.addAttribute("request", request);
        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute("request") RegistrationRequest request){
        registrationService.register(request);
        return "successful";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "confirm";
    }
}

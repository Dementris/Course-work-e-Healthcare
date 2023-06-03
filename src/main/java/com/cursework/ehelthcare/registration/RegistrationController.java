package com.cursework.ehelthcare.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The type Registration controller.
 */
@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Get form string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping
    public String getForm(Model model, RegistrationRequest request){
        model.addAttribute("request", request);
        return "registration";
    }

    /**
     * Register string.
     *
     * @param request the request
     * @return the string
     */
    @PostMapping
    public String register(@ModelAttribute("request") RegistrationRequest request){
        registrationService.register(request);
        return "successful";
    }

    /**
     * Confirm string.
     *
     * @param token the token
     * @return the string
     */
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "confirm";
    }
}

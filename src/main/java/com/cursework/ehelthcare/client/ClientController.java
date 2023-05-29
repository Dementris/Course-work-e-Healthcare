package com.cursework.ehelthcare.client;

import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/client")
public class ClientController {
    private final UserRepository repository;
    private final JwtService jwtService;

    @GetMapping("/home")
    public String getClientPage(@RequestParam String access_token, Model model){
        var email = jwtService.extractUsername(access_token);
        var user = repository.findByEmail(email).orElseThrow();
        model.addAttribute("user",user);
        return "home";
    }
}

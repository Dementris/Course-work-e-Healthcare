package com.cursework.ehelthcare.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserRepository repository;

    @GetMapping("/account/home")
    public String userHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
//        User user = repository.findByEmail(email)
//                .orElseThrow(() -> new IllegalStateException("User not found"));

//        model.addAttribute("firstName", user.getFirstName());
//        model.addAttribute("email", user.getEmail());

        return "user-home";
    }
}

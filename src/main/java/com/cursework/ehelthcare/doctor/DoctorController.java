package com.cursework.ehelthcare.doctor;

import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import com.cursework.ehelthcare.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/doctor")
public class DoctorController {

    private final UserRepository repository;
    private final JwtService jwtService;

    @GetMapping("/home")
    public String getDoctorPage(@RequestParam String access_token, Model model){
        var email = jwtService.extractUsername(access_token);
        var user = repository.findByEmail(email).orElseThrow();
        model.addAttribute("user",user);
        List<User> clients = repository.findAllByUserRole(UserRole.ROLE_USER);
        model.addAttribute("clients", clients);
        return "dochome";
    }
}

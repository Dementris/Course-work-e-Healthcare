package com.cursework.ehelthcare.client;

import com.cursework.ehelthcare.appointment.Appointment;
import com.cursework.ehelthcare.appointment.AppointmentRepository;
import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import com.cursework.ehelthcare.user.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Client controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/client")
public class ClientController {
    private final UserRepository repository;
    private final JwtService jwtService;

    private final HttpServletRequest httpServletRequest;

    private final AppointmentRepository appointmentRepository;


    /**
     * Get client page string.
     *
     * @param access_token the access token
     * @param model        the model
     * @return the string
     */
    @GetMapping("/home")
    public String getClientPage(@RequestParam String access_token, Model model){
        var email = jwtService.extractUsername(access_token);
        var user = repository.findByEmail(email).orElseThrow();
        List<User> doctors = repository.findAllByUserRole(UserRole.ROLE_ADMIN);
        List<Appointment> appointmentList = appointmentRepository.findAllByUser(user);
        model.addAttribute("token",access_token);
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("user",user);
        model.addAttribute("table",appointmentList);
        return "home";
    }


}

package com.cursework.ehelthcare.appointment;

import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import com.cursework.ehelthcare.user.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentController {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final JwtService jwtService;


    @PostMapping("/appointments/new")
    public String submitAppointmentForm(@ModelAttribute("appointment") Appointment appointment,
                                        @RequestParam("access_token") String access_token)  {
        String email = jwtService.extractUsername(access_token);
        User user = userRepository.findByEmail(email).orElseThrow();
        appointment.setUser(user);
        appointmentRepository.save(appointment);
        return "confirm-appointment";
    }

}

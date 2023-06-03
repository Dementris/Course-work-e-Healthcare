package com.cursework.ehelthcare.doctor;

import com.cursework.ehelthcare.appointment.Appointment;
import com.cursework.ehelthcare.appointment.AppointmentRepository;
import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.email.EmailSender;
import com.cursework.ehelthcare.token.TokenRepository;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import com.cursework.ehelthcare.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Doctor controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/doctor")
public class DoctorController {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final EmailSender emailSender;
    private final AppointmentRepository appointmentRepository;

    private final TokenRepository tokenRepository;

    /**
     * Get doctor page string.
     *
     * @param access_token the access token
     * @param model        the model
     * @return the string
     */
    @GetMapping("/home")
    public String getDoctorPage(@RequestParam String access_token, Model model){
        var email = jwtService.extractUsername(access_token);
        var user = repository.findByEmail(email).orElseThrow();
        model.addAttribute("user",user);
        List<Appointment> appointmentList = appointmentRepository.findAllByDoctor(user);
        model.addAttribute("clients", appointmentList);
        return "dochome";
    }

    /**
     * Confirm appointment string.
     *
     * @param appointmentId the appointment id
     * @return the string
     */
    @PostMapping("/appointments/{appointmentId}/confirm")
    public String confirmAppointment(@PathVariable("appointmentId") Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
        emailSender.send(appointment.getDoctor().getEmail(),
                buildSubmitedEmail(appointment.getDoctor().getFirstName(),
                        appointment.getUser().getFirstName(),
                        appointment.getDateTime().toString()));
        String token = tokenRepository.findAllValidTokenByUser(Math.toIntExact(appointment.getDoctor().getId())).get(0).getToken();
        return "redirect:/doctor/home?access_token="+token;
    }

    /**
     * Decline appointment string.
     *
     * @param appointmentId the appointment id
     * @return the string
     */
    @PostMapping("/appointments/{appointmentId}/decline")
    public String declineAppointment(@PathVariable("appointmentId") Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointmentRepository.delete(appointment);
        emailSender.send(appointment.getUser().getEmail(),
                buildDeclinedEmail(appointment.getDoctor().getFirstName(),
                        appointment.getUser().getFirstName(),
                        appointment.getDateTime().toString()));
        return "redirect:/doctor/home";
    }

    private String buildSubmitedEmail(String doctorName, String name, String dateTime) {
        return "<div style=\"font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; margin: 0;\">\n" +
                "    <div style=\"background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.2);\">\n" +
                "        <h2 style=\"font-size: 28px; margin-bottom: 20px; text-align: center;\">Appointment Confirmation</h2>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 30px; text-align: center;\">Dear " + name + ",</p>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 20px;\">Thank you for submitting your appointment request through our e-Healthcare website. The details of your appointment are as follows:</p>\n" +
                "        <ul style=\"font-size: 18px; margin-bottom: 20px; padding-left: 20px;\">\n" +
                "            <li>Doctor: " + doctorName + "</li>\n" +
                "            <li>Date and Time: " + dateTime + " </li>\n" +
                "        </ul>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 20px;\">To cancel your appointment, please click the link below:</p>\n" +
                "        <p style=\"font-size: 18px; margin-top: 20px;\">Please note that your appointment will be automatically confirmed if no action is taken within 24 hours.</p>\n" +
                "    </div>\n" +
                "</div>";
    }
    private String buildDeclinedEmail(String doctorName, String name, String dateTime) {
        return "<div style=\"font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; margin: 0;\">\n" +
                "    <div style=\"background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.2);\">\n" +
                "        <h2 style=\"font-size: 28px; margin-bottom: 20px; text-align: center;\">Appointment Declined</h2>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 30px; text-align: center;\">Dear " + name + ",</p>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 20px;\">We regret to inform you that your appointment request for the following details has been declined:</p>\n" +
                "        <ul style=\"font-size: 18px; margin-bottom: 20px; padding-left: 20px;\">\n" +
                "            <li>Doctor: " + doctorName + "</li>\n" +
                "            <li>Date and Time: " + dateTime + " </li>\n" +
                "        </ul>\n" +
                "        <p style=\"font-size: 18px; margin-bottom: 20px;\">If you have any questions or need further assistance, please contact our support team.</p>\n" +
                "        <p style=\"font-size: 18px; margin-top: 20px;\">We apologize for any inconvenience caused and thank you for your understanding.</p>\n" +
                "    </div>\n" +
                "</div>";
    }


}

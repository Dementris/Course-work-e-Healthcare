package com.cursework.ehelthcare.personal;

import com.cursework.ehelthcare.auth.AuthenticationRequest;
import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import com.cursework.ehelthcare.user.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The type Personal page controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/page")
public class PersonalPageController {

    private final JwtService jwtService;
    private final UserRepository repository;

    /**
     * Redirection point redirect view.
     *
     * @param access_token the access token
     * @param model        the model
     * @return the redirect view
     */
    @GetMapping("/dir")
    public RedirectView redirectionPoint(@RequestParam String access_token,
                                         Model model){
        var email = jwtService.extractUsername(access_token);
        var user = repository.findByEmail(email).orElseThrow();
        if (user.getUserRole() == UserRole.ROLE_ADMIN){
            return new RedirectView("/doctor/home?access_token="+access_token);
        }
        return new RedirectView("/client/home?access_token="+access_token);
    }
}

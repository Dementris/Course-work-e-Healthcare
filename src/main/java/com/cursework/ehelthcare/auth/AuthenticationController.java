package com.cursework.ehelthcare.auth;

import com.cursework.ehelthcare.config.JwtService;
import com.cursework.ehelthcare.user.User;
import com.cursework.ehelthcare.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final JwtService jwtService;

  private final UserRepository repository;

  @GetMapping("/form")
  public String showLoginForm(Model model) {
    model.addAttribute("authenticationRequest", new AuthenticationRequest());
    return "login";
  }

  @PostMapping("/authenticate")
  public String authenticate(
          @ModelAttribute("authenticationRequest") AuthenticationRequest request,
          Model model
  ) {
    var token = service.authenticate(request);
    var email = jwtService.extractUsername(token.getAccessToken());
    var user = repository.findByEmail(email).orElseThrow();
    model.addAttribute("user",user);
    model.addAttribute("accessToken", token.getAccessToken());
    model.addAttribute("refreshToken", token.getRefreshToken());
    return "home";
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}

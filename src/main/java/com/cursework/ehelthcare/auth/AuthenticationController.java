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
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @GetMapping("/form")
  public String showLoginForm(Model model) {
    model.addAttribute("authenticationRequest", new AuthenticationRequest());
    return "login";
  }

  @PostMapping("/authenticate")
  public RedirectView authenticate(
          @ModelAttribute("authenticationRequest") AuthenticationRequest request,
          HttpServletRequest httpServletRequest,
          HttpServletResponse response
  ) {
    var token = service.authenticate(request);
    System.out.println(token.getAccessToken());
    httpServletRequest.getSession().setAttribute("access_token", token.getAccessToken());

    return new RedirectView("/page/dir?access_token="+token.getAccessToken());
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}

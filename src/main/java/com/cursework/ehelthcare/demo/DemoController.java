package com.cursework.ehelthcare.demo;


import com.cursework.ehelthcare.auth.AuthenticationResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello(AuthenticationResponse response) {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}

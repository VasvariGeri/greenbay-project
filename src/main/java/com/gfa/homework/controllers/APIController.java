package com.gfa.homework.controllers;

import com.gfa.homework.security.AuthenticationRequest;
import com.gfa.homework.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

  private UserService userService;

  public APIController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    return ResponseEntity.ok(userService.authenticate(authenticationRequest));
  }

  @GetMapping("/hello")
  public ResponseEntity<?> displayHello() {
    return ResponseEntity.ok("Hello");
  }
}

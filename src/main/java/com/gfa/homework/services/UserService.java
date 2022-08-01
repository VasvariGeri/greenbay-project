package com.gfa.homework.services;

import com.gfa.homework.exceptions.AuthorizationFailureException;
import com.gfa.homework.exceptions.InvalidLoginCredentialsException;
import com.gfa.homework.exceptions.MissingUsernameOrPasswordException;
import com.gfa.homework.models.User;
import com.gfa.homework.repositories.UserRepository;
import com.gfa.homework.security.AuthenticationRequest;
import com.gfa.homework.security.AuthenticationResponse;
import com.gfa.homework.security.JwtUtil;
import com.gfa.homework.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private UserRepository userRepository;
  private AuthenticationManager authenticationManager;
  private MyUserDetailsService userDetailsService;
  private JwtUtil jwtTokenUtil;

  @Autowired
  public UserService(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      MyUserDetailsService userDetailsService,
      JwtUtil jwtTokenUtil) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  private void validateLoginCredentials(AuthenticationRequest authenticationRequestDTO) {
    if (authenticationRequestDTO.getUsername() == null
        || authenticationRequestDTO.getUsername().isBlank()
        || authenticationRequestDTO.getPassword() == null
        || authenticationRequestDTO.getPassword().isBlank()) {
      throw new MissingUsernameOrPasswordException(
          "Field username and/or field password was empty!");
    }
  }

  private void authenticateRequest(AuthenticationRequest authenticationRequestDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword()));
    } catch (BadCredentialsException e) {
      throw new InvalidLoginCredentialsException("Username and/or password was incorrect!");
    }
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    validateLoginCredentials(authenticationRequest);
    authenticateRequest(authenticationRequest);
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    return new AuthenticationResponse(jwt);
  }

  public User getUserFromToken(String header) {
    String token = header.substring(7);
    String username = jwtTokenUtil.extractUsername(token);
    Optional<User> userOptional = userRepository.findByUsername(username);
    User user;
    if (userOptional.isEmpty()) {
      throw new AuthorizationFailureException("User not found in database");
    } else {
      return user = userRepository.findByUsername(username).get();
    }
  }
}

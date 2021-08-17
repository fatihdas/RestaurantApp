package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.auth.TokenManager;
import com.restaurantapp.restapp.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return new  ResponseEntity(tokenManager.generateToken(request.getUsername()), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }
}

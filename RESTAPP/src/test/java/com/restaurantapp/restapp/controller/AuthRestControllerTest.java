package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.security.AuthRequest;
import com.restaurantapp.restapp.security.JwtUtil;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthRestControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthRestController authRestController;

    @Test
    public void createToken() {
        AuthRequest request = AuthRequest.builder()
                .username("testname")
                .password("testpass")
                .build();
    }
}
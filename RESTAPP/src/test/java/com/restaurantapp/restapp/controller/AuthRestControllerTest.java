package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.security.JwtUtil;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

@RunWith(MockitoJUnitRunner.class)
public class AuthRestControllerTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthRestController authRestController;

    @Test
    public void whenCreateTokenCalledByAuthRequest_thenReturnToken() {

    }
}
package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurantapp.restapp.model.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("home")
public class HomeController {

    private final UserServiceImpl userService;

    public HomeController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest request){

        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}

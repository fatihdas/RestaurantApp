package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/addAddress")
    public ResponseEntity<UserDto> addUserAddress(@Valid @RequestBody CreateAddressRequest request, @PathVariable long userId) {

        return new ResponseEntity<>(userService.addAddress(request, userId), HttpStatus.OK);
    }
}

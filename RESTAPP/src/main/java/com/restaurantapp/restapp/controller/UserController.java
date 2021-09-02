package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @PutMapping("changeRole/{userId}/{role}")
    public ResponseEntity<UserDto> changeUserRole(@PathVariable String role, @PathVariable long userId) {
        return new ResponseEntity<>(userService.updateUserRole(role, userId), HttpStatus.OK);
    }

    @GetMapping("getAddress/{userId}")
    public ResponseEntity<List<AddressDto>> getUserAddresses(@PathVariable long userId) {
        return new ResponseEntity<>(userService.getUserAdresses(userId), HttpStatus.OK);
    }

}

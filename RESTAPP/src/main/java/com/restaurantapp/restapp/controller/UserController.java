package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userServiceImpl.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userServiceImpl.getUser(id), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userServiceImpl.getUserByName(name), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest request, @PathVariable long id) {
        return new ResponseEntity<>(userServiceImpl.updateUser(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {

        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}

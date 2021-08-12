package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserRequest request);

    List<UserDto> getAllUsers();

    UserDto getUser(long id);

    UserDto getUserByName(String name);

    UserDto updateUser(UpdateUserRequest request, long id);

    void deleteUser(long id);
}

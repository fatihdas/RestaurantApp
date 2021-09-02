package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserRequest request);

    UserDto updateUserRole(String role, long userId);

    List<UserDto> getAllUsers();

    UserDto getUser(long id);

    UserDto getUserByName(String name);

    String updateUser(UpdateUserRequest request, long id);

    void deleteUser(long id);

    List<AddressDto> getUserAdresses(long userId);
}

package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserRequest request);

    UserDto updateUserRole(String role, long userId);

    List<UserDto> getAllUsers();

    UserDto getUser(long id);

    UserDto getUserDtoByName(String name);

    User getUserByName(String name);

    String updateUser(UpdateUserRequest request, long id);

    void deleteUser(long id);

    boolean hasRole(UserRoles userRoles);

    boolean isOwner(long ownerId);

}

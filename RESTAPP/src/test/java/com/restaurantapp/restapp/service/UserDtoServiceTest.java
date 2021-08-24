package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void save() {

        User user = this.generateUser();

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        com.restaurantapp.restapp.model.dto.UserDto createUser = userServiceImpl.createUser(new CreateUserRequest());

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void getAll() {

        List<User> userList = new ArrayList<>();
        userList.add(this.generateUser());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<com.restaurantapp.restapp.model.dto.UserDto> createUserList = userServiceImpl.getAllUsers();

        Assertions.assertEquals(userList, createUserList);
    }

    @Test
    public void getById() {

        User user = this.generateUser();

        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        com.restaurantapp.restapp.model.dto.UserDto createUser = userServiceImpl.getUser(2);

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void update() {

        User user = this.generateUser();

        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        String createUser = userServiceImpl.updateUser(new UpdateUserRequest(),13);

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void delete() {

        userRepository.deleteById(2L);
        Mockito.verify(userServiceImpl).deleteUser(2L);
    }

    private User generateUser() {
        return User.builder()
                .name("testname")
                .email("test@mail.com")
//                .roles(Roles.ADMIN)
                .build();
    }

}
package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.model.enumerated.Roles;
import com.restaurantapp.restapp.repository.UserRepository;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void save() {

        User user = this.generateUser();

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User createUser = userService.save(new User());

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void getAll() {

        List<User> userList = new ArrayList<>();
        userList.add(this.generateUser());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> createUserList = userService.getAll();

        Assertions.assertEquals(userList, createUserList);
    }

    @Test
    public void getById() {

        User user = this.generateUser();

        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        User createUser = userService.getById(2);

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void update() {

        User user = this.generateUser();

        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User createUser = userService.update(new User());

        Assertions.assertEquals(user, createUser);
    }

    @Test
    public void delete() {

        userRepository.deleteById(2L);
        Mockito.verify(userRepository).deleteById(2L);

        String deleteUser = userService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteUser);
    }

    private User generateUser() {
        return User.builder()
                .name("testname")
                .email("test@mail.com")
                .roles(Roles.ADMIN)
                .build();
    }

}
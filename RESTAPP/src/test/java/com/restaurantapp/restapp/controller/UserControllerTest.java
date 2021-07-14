package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class UserControllerTest {

    @Test
    void addUser() {

        User user = new User();

        user.setId(1234);
        user.setEmail("test@mail.com");
        user.setName("testname");
        user.setPassword("testpass");
        user.setRestaurants(Arrays.asList());
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
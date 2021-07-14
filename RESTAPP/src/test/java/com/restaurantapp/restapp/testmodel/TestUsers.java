package com.restaurantapp.restapp.testmodel;

import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.model.enumerated.Roles;

import java.util.ArrayList;
import java.util.List;

public class TestUsers {

    public List<User> userList(){
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setRoles(Roles.ADMIN);
        user1.setName("test1name");
        user1.setEmail("test1@mail.com");
        user1.setPassword("test1password");

        User user2 = new User();
        user2.setRoles(Roles.BUYER);
        user2.setName("test2name");
        user2.setEmail("test2@mail.com");
        user2.setPassword("test2password");

        users.add(user1);
        users.add(user2);

        return users;
    }


}

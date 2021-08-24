package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserIdToEntityConverter {

    private final UserServiceImpl userService;

    public UserIdToEntityConverter(UserServiceImpl userService) {
        this.userService = userService;
    }

    public User convert(long id) {

        return userService.getUserById(id);

    }
}

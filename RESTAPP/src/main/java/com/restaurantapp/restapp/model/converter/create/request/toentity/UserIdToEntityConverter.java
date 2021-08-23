package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserIdToEntityConverter {

    private final UserServiceImpl userService;
    private final UserDtoToEntityConverter userDtoToEntityConverter;

    public UserIdToEntityConverter(UserServiceImpl userService, UserDtoToEntityConverter userDtoToEntityConverter) {
        this.userService = userService;
        this.userDtoToEntityConverter = userDtoToEntityConverter;
    }

    public User convert(long id){

        return userDtoToEntityConverter.convert(userService.getUser(id));

    }
}

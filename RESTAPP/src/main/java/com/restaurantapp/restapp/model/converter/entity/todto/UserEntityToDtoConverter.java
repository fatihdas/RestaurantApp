package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToDtoConverter {

    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserRolesList(user.getRoles());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());

        return userDto;
    }
}

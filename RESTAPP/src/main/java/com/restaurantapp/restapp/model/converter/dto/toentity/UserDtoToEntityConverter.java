package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoToEntityConverter {

    public User convert(UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .roles(userDto.getRolesList())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}

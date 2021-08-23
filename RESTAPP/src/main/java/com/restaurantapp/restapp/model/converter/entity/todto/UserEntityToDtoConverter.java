package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserEntityToDtoConverter {


    public com.restaurantapp.restapp.model.dto.UserDto convert(User user) {

        return com.restaurantapp.restapp.model.dto.UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}

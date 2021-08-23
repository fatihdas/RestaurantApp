package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserEntityToDtoConverter {

    private final AddressEntityToDtoConverter addressEntityToDtoConverter;

    public UserEntityToDtoConverter(AddressEntityToDtoConverter addressEntityToDtoConverter) {
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
    }

    public com.restaurantapp.restapp.model.dto.UserDto convert(User user) {

        return com.restaurantapp.restapp.model.dto.UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addressDtoList(user.getAddressList().stream().map(addressEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}

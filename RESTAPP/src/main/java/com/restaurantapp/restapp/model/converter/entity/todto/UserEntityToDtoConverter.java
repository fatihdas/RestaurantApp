package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserEntityToDtoConverter {

    private final AddressEntityToDtoConverter addressEntityToDtoConverter;
    private final CommentEntityToDtoConverter commentEntityToDtoConverter;
    private final RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;

    public UserEntityToDtoConverter(@Lazy AddressEntityToDtoConverter addressEntityToDtoConverter,
                                    @Lazy CommentEntityToDtoConverter commentEntityToDtoConverter,
                                    @Lazy RestaurantEntityToDtoConverter restaurantEntityToDtoConverter) {
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.commentEntityToDtoConverter = commentEntityToDtoConverter;
        this.restaurantEntityToDtoConverter = restaurantEntityToDtoConverter;
    }

    public com.restaurantapp.restapp.model.dto.UserDto convert(User user) {

        return com.restaurantapp.restapp.model.dto.UserDto.builder()
                .id(user.getId())
                .addressDtoList(user.getAddressList().stream().map(addressEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}

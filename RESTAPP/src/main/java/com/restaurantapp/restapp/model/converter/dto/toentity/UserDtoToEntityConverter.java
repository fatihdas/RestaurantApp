package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoToEntityConverter {

    private final CommentDtoToEntityConverter commentDtoToEntityConverter;
    private final AddressDtoToEntityConverter addressDtoToEntityConverter;

    public UserDtoToEntityConverter(CommentDtoToEntityConverter commentDtoToEntityConverter, AddressDtoToEntityConverter addressDtoToEntityConverter) {
        this.commentDtoToEntityConverter = commentDtoToEntityConverter;
        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
    }

    public User convert(UserDto userDto) {

        return User.builder()
                .roles(userDto.getRoles())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .addressList(userDto.getAddressDtoList().stream().map(addressDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}

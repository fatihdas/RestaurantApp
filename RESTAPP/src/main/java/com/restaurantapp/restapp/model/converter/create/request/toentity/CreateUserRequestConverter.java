package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.AddressDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CommentDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateUserRequestConverter {

    private final AddressDtoToEntityConverter addressDtoToEntityConverter;
    private final CommentDtoToEntityConverter commentDtoToEntityConverter;

    public CreateUserRequestConverter(AddressDtoToEntityConverter addressDtoToEntityConverter,
                                      CommentDtoToEntityConverter commentDtoToEntityConverter) {
        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
        this.commentDtoToEntityConverter = commentDtoToEntityConverter;
    }

    public User convert(CreateUserRequest request) {

        return User.builder()
                .name(request.getName())
                .addressList(request.getAddressDtoList().stream().map(addressDtoToEntityConverter::convert)
                        .collect(Collectors.toList()))
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();

    }
}

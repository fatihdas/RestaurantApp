package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CreateUserRequestConverter {

    private final CreateAddressRequestConverter createAddressRequestConverter;

    public CreateUserRequestConverter(CreateAddressRequestConverter createAddressRequestConverter) {
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public User convert(CreateUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setId(request.getId());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setRoles(request.getRoles());
        Address address = createAddressRequestConverter.convert(request.getCreateAddressRequest());
        user.setAddressList(Collections.singletonList(address));

        return user;

    }
}

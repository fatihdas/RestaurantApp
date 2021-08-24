package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateUserRequestConverter {

    private final CreateAddressRequestConverter createAddressRequestConverter;

    public CreateUserRequestConverter(CreateAddressRequestConverter createAddressRequestConverter) {
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public User convert(CreateUserRequest request) {

        User user = new User();
        List<Roles> roles = new ArrayList<>();
        roles.add(request.getRoles());
        user.setRoles(roles);
        user.setEmail(request.getEmail());
        user.setId(request.getId());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        List<Address> addressList = new ArrayList<>();
        addressList.add(createAddressRequestConverter.convert(request.getCreateAddressRequest()));
        user.setAddressList(addressList);

        return user;

    }
}

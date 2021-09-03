package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.County;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateAddressRequestConverter {

    public Address convert(CreateAddressRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Address address = new Address();
        address.setUser(User.builder().id(request.getUserId()).build());
        address.setContent(request.getContent());
        address.setCounty(County.builder().id(request.getCountyId()).build());
        address.setBranch(Branch.builder().id(request.getBranchId()).build());

        return address;
    }
}

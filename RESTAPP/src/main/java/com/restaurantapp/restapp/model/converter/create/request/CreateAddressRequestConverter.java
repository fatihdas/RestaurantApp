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

        if (request == null ||
                (request.getBranchId() == 0 && request.getUserId() == 0) ||
                (request.getBranchId() !=0 && request.getUserId() !=0)) {
            throw new IllegalArgumentException("invalid request!");
        }
        Address address = new Address();
        address.setContent(request.getContent());
        County county = new County();
        county.setId(request.getCountyId());
        address.setCounty(county);
        if (request.getBranchId() != 0) {
            Branch branch = new Branch();
            branch.setId(request.getBranchId());
            address.setBranch(branch);
        } else {
            User user = new User();
            user.setId(request.getUserId());
            address.setUser(user);
        }

        return address;
    }
}

package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateBranchRequestConverter {

    private final CreateMenuRequestConverter createMenuRequestConverter;
    private final CreateAddressRequestConverter createAddressRequestConverter;

    public CreateBranchRequestConverter(CreateMenuRequestConverter createMenuRequestConverter,
                                        CreateAddressRequestConverter createAddressRequestConverter) {
        this.createMenuRequestConverter = createMenuRequestConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
    }

    public Branch convert(CreateBranchRequest request) {

        return Branch.builder()
                .id(request.getId())
                .name(request.getName())
                .status(request.getStatus())
                .menu(createMenuRequestConverter.convert(request.getCreateMenuRequest()))
                .address(createAddressRequestConverter.convert(request.getCreateAddressRequest()))
                .build();

    }
}

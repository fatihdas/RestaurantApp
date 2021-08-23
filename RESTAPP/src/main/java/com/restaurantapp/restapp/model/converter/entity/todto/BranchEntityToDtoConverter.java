package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchEntityToDtoConverter {

    private final MenuEntityToDtoConverter menuEntityToDtoConverter;
    private final AddressEntityToDtoConverter addressEntityToDtoConverter;

    public BranchEntityToDtoConverter(MenuEntityToDtoConverter menuEntityToDtoConverter,
                                      AddressEntityToDtoConverter addressEntityToDtoConverter) {
        this.menuEntityToDtoConverter = menuEntityToDtoConverter;
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
    }

    public BranchDto convert(Branch branch) {

        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .status(branch.getStatus())
                .menuDto(menuEntityToDtoConverter.convert(branch.getMenu()))
                .addressDto(addressEntityToDtoConverter.convert(branch.getAddress()))
                .build();
    }
}

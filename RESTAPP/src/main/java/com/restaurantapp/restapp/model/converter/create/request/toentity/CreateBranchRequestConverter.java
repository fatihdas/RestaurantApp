package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.AddressDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CommentDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.MenuDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateBranchRequestConverter {

    private final CommentDtoToEntityConverter commentDtoToEntityConverter;
    private final MenuDtoToEntityConverter menuDtoToEntityConverter;
    private final AddressDtoToEntityConverter addressDtoToEntityConverter;

    public CreateBranchRequestConverter(CommentDtoToEntityConverter commentDtoToEntityConverter,
                                        MenuDtoToEntityConverter menuDtoToEntityConverter,
                                        AddressDtoToEntityConverter addressDtoToEntityConverter) {
        this.commentDtoToEntityConverter = commentDtoToEntityConverter;
        this.menuDtoToEntityConverter = menuDtoToEntityConverter;
        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
    }


    public Branch convert(CreateBranchRequest request) {

        return Branch.builder()
                .address(addressDtoToEntityConverter.convert(request.getAddressDto()))
                .status(request.getStatus())
                .menu(menuDtoToEntityConverter.convert(request.getMenuDto()))
                .name(request.getName())
                .build();

    }
}

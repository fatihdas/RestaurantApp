package com.restaurantapp.restapp.model.converter.update.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.AddressDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.CommentDtoToEntityConverter;
import com.restaurantapp.restapp.model.converter.dto.toentity.MenuDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UpdateBranchRequestConverter {

    private final MenuDtoToEntityConverter menuDtoToEntityConverter;
    private final CommentDtoToEntityConverter commentDtoToEntityConverter;
    private final AddressDtoToEntityConverter addressDtoToEntityConverter;

    public UpdateBranchRequestConverter(MenuDtoToEntityConverter menuDtoToEntityConverter,
                                        CommentDtoToEntityConverter commentDtoToEntityConverter,
                                        AddressDtoToEntityConverter addressDtoToEntityConverter) {
        this.menuDtoToEntityConverter = menuDtoToEntityConverter;
        this.commentDtoToEntityConverter = commentDtoToEntityConverter;
        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
    }

    public Branch convert(UpdateBranchRequest request) {

        return Branch.builder()
                .name(request.getName())
                .menu(menuDtoToEntityConverter.convert(request.getMenuDto()))
                .address(addressDtoToEntityConverter.convert(request.getAddressDto()))
                .build();
    }
}

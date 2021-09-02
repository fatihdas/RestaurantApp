package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BranchEntityToDtoConverter {

    private final MenuEntityToDtoConverter menuEntityToDtoConverter;
    private final AddressEntityToDtoConverter addressEntityToDtoConverter;
    private final CommentEntityToDtoConverter commentEntityToDtoConverter;

    public BranchEntityToDtoConverter(MenuEntityToDtoConverter menuEntityToDtoConverter,
                                      AddressEntityToDtoConverter addressEntityToDtoConverter, CommentEntityToDtoConverter commentEntityToDtoConverter) {
        this.menuEntityToDtoConverter = menuEntityToDtoConverter;
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.commentEntityToDtoConverter = commentEntityToDtoConverter;
    }

    public BranchDto convert(Branch branch) {

        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .status(branch.getStatus())
                .menuDto(menuEntityToDtoConverter.convert(branch.getMenu()))
                .addressDto(addressEntityToDtoConverter.convert(branch.getAddress()))
                .commentDtos(branch.getCommentList().stream().map(commentEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}

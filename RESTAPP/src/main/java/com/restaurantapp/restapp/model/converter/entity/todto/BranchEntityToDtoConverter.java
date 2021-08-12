package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BranchEntityToDtoConverter {

    private final AddressEntityToDtoConverter addressEntityToDtoConverter;
    private final CommentEntityToDtoConverter commentEntityToDtoConverter;
    private final MenuEntityToDtoConverter menuEntityToDtoConverter;
    private final RestaurantEntityToDtoConverter restaurantEntityToDtoConverter;

    public BranchEntityToDtoConverter(@Lazy AddressEntityToDtoConverter addressEntityToDtoConverter, @Lazy CommentEntityToDtoConverter commentEntityToDtoConverter,
                                      @Lazy MenuEntityToDtoConverter menuEntityToDtoConverter, @Lazy RestaurantEntityToDtoConverter restaurantEntityToDtoConverter) {
        this.addressEntityToDtoConverter = addressEntityToDtoConverter;
        this.commentEntityToDtoConverter = commentEntityToDtoConverter;
        this.menuEntityToDtoConverter = menuEntityToDtoConverter;
        this.restaurantEntityToDtoConverter = restaurantEntityToDtoConverter;
    }

    public BranchDto convert(Branch branch) {

        return BranchDto.builder()
                .id(branch.getId())
                .addressDto(addressEntityToDtoConverter.convert(branch.getAddress()))
                .commentDtos(branch.getCommentList().stream().map(commentEntityToDtoConverter::convert)
                        .collect(Collectors.toList()))
                .menuDto(menuEntityToDtoConverter.convert(branch.getMenu()))
                .name(branch.getName())
                .status(branch.getStatus())
                .build();
    }
}

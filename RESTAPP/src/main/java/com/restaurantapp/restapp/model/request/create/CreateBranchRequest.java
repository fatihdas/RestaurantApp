package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBranchRequest {

    @NotNull
    private String name;

    @NotNull
    private Status status;

    @NotNull
    private List<CommentDto> commentDtos;

    @NotNull
    private MenuDto menuDto;

    @NotNull
    private AddressDto addressDto;

}

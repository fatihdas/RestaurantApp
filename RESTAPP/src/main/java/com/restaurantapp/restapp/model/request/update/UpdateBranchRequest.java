package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBranchRequest {

    private String name;

    private List<CommentDto> commentDtos;

    private MenuDto menuDto;

    private AddressDto addressDto;

}

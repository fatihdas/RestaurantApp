package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantRequest {

    @NotNull
    private String name;

    @NotNull
    private UserDto userDto;

    @NotNull
    private List<BranchDto> branchDtoList;

}

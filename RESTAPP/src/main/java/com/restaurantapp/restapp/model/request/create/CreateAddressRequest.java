package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressRequest {

    @NotNull
    private CityDto cityDto;

    @NotNull
    private CountyDto countyDto;

    @NotNull
    private String content;

}

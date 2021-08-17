package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressRequest {

    @NotNull
    private long id;

    @NotNull
    private String cityName;

    @NotNull
    private String countyName;

    @NotBlank
    private String content;

}

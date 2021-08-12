package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.CountyDto;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCityRequest {

    @NotNull
    private String name;

    @NotNull
    private List<CountyDto> countyDtoList;

}

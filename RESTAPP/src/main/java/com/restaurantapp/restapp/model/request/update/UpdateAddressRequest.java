package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {

    private CityDto cityDto;

    private CountyDto countyDto;

    private String content;

}

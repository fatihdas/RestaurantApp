package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.CountyDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCityRequest {

    private String name;

    private List<CountyDto> countyDtoList;

}

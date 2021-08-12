package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDto implements Serializable {

    private long id;

    private CityDto cityDto;

    private CountyDto countyDto;

    private String content;

}

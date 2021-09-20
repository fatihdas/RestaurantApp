package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.County;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto implements Serializable {

    private long id;

    private String name;

}

package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.City;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CountyDto implements Serializable {

    private long id;

    private String name;

}

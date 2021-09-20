package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto implements Serializable {

    private long id;

    private String name;

    private long userId;

}

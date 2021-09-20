package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDto implements Serializable {

    private long id;

    private String name;

    private float price;

    private long menuId;

}

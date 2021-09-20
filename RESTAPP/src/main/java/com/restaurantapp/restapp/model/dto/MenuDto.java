package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Meal;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto implements Serializable {

    private long id;

    private String name;

    private List<MealDto> mealDtoList;

}

package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.MealDto;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMenuRequest {

    @NotNull
    private String name;

    @NotNull
    private List<MealDto> mealDtoList;

}

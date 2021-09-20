package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.MealDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuRequest {

    private String name;

}

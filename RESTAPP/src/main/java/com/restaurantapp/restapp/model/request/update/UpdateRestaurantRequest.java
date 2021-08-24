package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.BranchDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRestaurantRequest {

    private String name;

}

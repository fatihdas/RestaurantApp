package com.restaurantapp.restapp.model.request.update;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UpdateMealRequest {

    private String name;

    private float price;

}

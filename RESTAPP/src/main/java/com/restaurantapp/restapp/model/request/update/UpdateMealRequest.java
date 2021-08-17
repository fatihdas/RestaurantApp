package com.restaurantapp.restapp.model.request.update;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMealRequest {

    private String name;

    private float price;

}

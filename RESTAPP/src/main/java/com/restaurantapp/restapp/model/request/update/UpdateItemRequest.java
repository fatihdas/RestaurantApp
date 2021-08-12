package com.restaurantapp.restapp.model.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateItemRequest {

    private String name;

    private float price;

}

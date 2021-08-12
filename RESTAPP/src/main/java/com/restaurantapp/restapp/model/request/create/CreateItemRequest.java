package com.restaurantapp.restapp.model.request.create;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateItemRequest {

    @NotNull
    private String name;

    @NotNull
    private float price;

}

package com.restaurantapp.restapp.model.request.create;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMealRequest {

    @NotNull
    private long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    private long menuId;

}

package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.ItemDto;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMealRequest {

    @NotNull
    private String name;

    @NotNull
    private float price;

    @NotNull
    private List<ItemDto> itemDtoList;

}

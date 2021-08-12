package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.ItemDto;
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

    private List<ItemDto> itemDtoList;

}

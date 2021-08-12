package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MealDto implements Serializable {

    private long id;

    private String name;

    private float price;

    private List<ItemDto> itemDtoList;

}

package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ItemDto implements Serializable {

    private long id;

    private String name;

    private float price;

}

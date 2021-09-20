package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountyDto implements Serializable {

    private long id;

    private String name;

    private long cityId;
}

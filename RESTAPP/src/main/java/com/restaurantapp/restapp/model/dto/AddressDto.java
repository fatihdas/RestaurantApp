package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDto implements Serializable {

    private long id;

    private String cityName;

    private String countyName;

    private String content;

}

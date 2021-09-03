package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDto implements Serializable {

    private long id;

    private String countyName;

    private long countyId;

    private long userId;

    private long branchId;

    private String content;
}

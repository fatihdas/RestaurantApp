package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.enumerated.Status;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BranchDto implements Serializable {

    private long id;

    private String name;

    private Status status;

    private MenuDto menuDto;

    private AddressDto addressDto;

}

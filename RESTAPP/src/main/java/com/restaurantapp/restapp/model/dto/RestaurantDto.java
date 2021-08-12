package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.User;
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
public class RestaurantDto implements Serializable {

    private long id;

    private String name;

    private UserDto userDto;

    private List<BranchDto> branchDtoList;

}

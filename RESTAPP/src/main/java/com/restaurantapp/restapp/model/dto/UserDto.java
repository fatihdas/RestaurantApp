package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private long id;

    private String name;

    private String password;

    private String email;

    private List<UserRoles> userRolesList;

}

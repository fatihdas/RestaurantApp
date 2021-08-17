package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.enumerated.Roles;
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
public class UserDto implements Serializable {

    private long id;

    private String name;

    private String password;

    private String email;

    private List<Roles> roles;

    private List<AddressDto> addressDtoList;

    private List<CommentDto> commentDtoList;

}

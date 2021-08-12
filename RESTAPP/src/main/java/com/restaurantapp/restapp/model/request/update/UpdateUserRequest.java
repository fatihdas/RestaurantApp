package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String name;

    private String password;

    private String email;

    private Roles roles;

    private List<AddressDto> addressDtoList;

    private List<CommentDto> commentDtoList;

}

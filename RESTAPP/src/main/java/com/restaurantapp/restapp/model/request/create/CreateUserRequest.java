package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private Roles roles;

    @NotNull
    private List<AddressDto> addressDtoList;

    @NotNull
    private List<CommentDto> commentDtoList;

}

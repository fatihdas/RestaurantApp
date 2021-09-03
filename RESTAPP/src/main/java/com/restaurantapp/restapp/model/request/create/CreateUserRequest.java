package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotNull
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private List<UserRoles> roles = Arrays.asList(UserRoles.BUYER);

    @NotNull
    private CreateAddressRequest createAddressRequest;
}

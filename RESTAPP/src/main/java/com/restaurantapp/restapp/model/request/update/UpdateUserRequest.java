package com.restaurantapp.restapp.model.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String password;

    private String email;

}

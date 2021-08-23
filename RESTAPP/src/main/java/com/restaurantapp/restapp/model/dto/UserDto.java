package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;

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

}

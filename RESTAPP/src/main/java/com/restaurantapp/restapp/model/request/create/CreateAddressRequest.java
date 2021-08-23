package com.restaurantapp.restapp.model.request.create;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressRequest {

    @NotNull
    private long id;

    @NotNull
    private String cityName;

    @NotNull
    private String countyName;

    @NotBlank
    private String content;

}

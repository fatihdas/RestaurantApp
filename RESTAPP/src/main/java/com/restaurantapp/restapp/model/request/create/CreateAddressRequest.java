package com.restaurantapp.restapp.model.request.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressRequest {

    @NotNull
    private long cityId;

    @NotNull
    private long countyId;

    @NotNull
    private long userId;

    @NotNull
    private long branchId;

    @NotBlank
    private String content;

}

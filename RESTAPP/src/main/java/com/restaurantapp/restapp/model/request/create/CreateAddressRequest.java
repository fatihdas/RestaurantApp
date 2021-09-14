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
    private long countyId;

    private long branchId;

    private long userId;

    @NotBlank
    private String content;

}

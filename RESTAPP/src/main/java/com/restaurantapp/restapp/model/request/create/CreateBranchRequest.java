package com.restaurantapp.restapp.model.request.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBranchRequest {

    @NotNull
    private long id;

    @NotBlank
    private String name;

    @NotNull
    private CreateAddressRequest createAddressRequest;

    @NotNull
    private long restaurantId;

}

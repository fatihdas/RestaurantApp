package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.entity.enumerated.Status;
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
    @Builder.Default
    private Status status = Status.WAITING;

    @NotNull
    private CreateMenuRequest createMenuRequest;

    @NotNull
    private CreateAddressRequest createAddressRequest;

}

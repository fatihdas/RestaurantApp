package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
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

    @Builder.Default
    private BranchStatus branchStatus = BranchStatus.WAITING;

    @NotNull
    private CreateMenuRequest createMenuRequest;

    @NotNull
    private CreateAddressRequest createAddressRequest;

    @NotNull
    private long restaurantId;

}

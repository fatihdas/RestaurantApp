package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import lombok.*;

import javax.validation.constraints.*;

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
    private MenuDto menuDto;

    @NotNull
    private AddressDto addressDto;

}

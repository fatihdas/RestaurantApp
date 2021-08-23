package com.restaurantapp.restapp.model.request.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMenuRequest {

    @NotNull
    private long id;

    @NotBlank
    private String name;

    private List<CreateMealRequest> createMealRequestList;

}

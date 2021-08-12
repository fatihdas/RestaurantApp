package com.restaurantapp.restapp.model.request.create;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCountyRequest {

    @NotNull
    private String name;

}

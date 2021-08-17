package com.restaurantapp.restapp.model.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {

    private String cityName;

    private String countyName;

    private String content;

}

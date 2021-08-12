package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentRequest {

    @NotNull
    private UserDto userDto;

    @NotNull
    private String content;

    @NotNull
    private Date date;

}

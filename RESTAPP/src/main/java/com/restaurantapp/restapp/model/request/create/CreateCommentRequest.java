package com.restaurantapp.restapp.model.request.create;

import com.restaurantapp.restapp.model.dto.UserDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentRequest {

    @NotNull
    private long id;

    @NotNull
    private UserDto userDto;

    @NotBlank
    private String content;

    @NotNull
    private Date date;

}

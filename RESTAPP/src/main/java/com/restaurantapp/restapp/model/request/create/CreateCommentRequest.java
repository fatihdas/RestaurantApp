package com.restaurantapp.restapp.model.request.create;

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
    private long userId;

    @NotBlank
    private String content;

    @NotNull
    private int testePoint;

    @NotNull
    private int speedPoint;

    @NotNull
    private Date date;

    @NotNull
    private long branchId;
}

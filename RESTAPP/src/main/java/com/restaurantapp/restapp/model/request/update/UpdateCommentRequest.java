package com.restaurantapp.restapp.model.request.update;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCommentRequest {

    private String content;

    private Integer tastePoint;

    private Integer speedPoint;

}

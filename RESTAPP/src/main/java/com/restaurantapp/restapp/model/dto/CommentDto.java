package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto implements Serializable {

    private long id;

    private long userId;

    private String content;

    private int tastePoint;

    private int speedPoint;

    private Date date;

    private long branchId;
}

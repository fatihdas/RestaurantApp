package com.restaurantapp.restapp.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CommentDto implements Serializable {

    private long id;

    private UserDto userDto;

    private String content;

    private int tastePoint;

    private int speedPoint;

    private Date date;

    private BranchDto branchDto;

}

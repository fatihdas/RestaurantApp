package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.User;
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

    private Date date;

}

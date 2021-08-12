package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToEntityConverter {

    private final UserDtoToEntityConverter userDtoToEntityConverter;

    public CommentDtoToEntityConverter(@Lazy UserDtoToEntityConverter userDtoToEntityConverter) {
        this.userDtoToEntityConverter = userDtoToEntityConverter;
    }

    public Comment convert(CommentDto commentDto) {

        return Comment.builder()
                .user(userDtoToEntityConverter.convert(commentDto.getUserDto()))
                .date(commentDto.getDate())
                .content(commentDto.getContent())
                .build();
    }
}

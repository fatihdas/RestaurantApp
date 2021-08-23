package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter {

    private final UserEntityToDtoConverter userEntityToDtoConverter;

    public CommentEntityToDtoConverter(UserEntityToDtoConverter userEntityToDtoConverter) {
        this.userEntityToDtoConverter = userEntityToDtoConverter;
    }

    public CommentDto convert(Comment comment) {

        return CommentDto.builder()
                .id(comment.getId())
                .userDto(userEntityToDtoConverter.convert(comment.getUser()))
                .content(comment.getContent())
                .testePoint(comment.getTastePoint())
                .speedPoint(comment.getSpeedPoint())
                .date(comment.getDate())
                .build();
    }
}

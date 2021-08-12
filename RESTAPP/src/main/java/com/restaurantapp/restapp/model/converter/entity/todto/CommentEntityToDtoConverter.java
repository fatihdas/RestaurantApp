package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter {

    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final UserEntityToDtoConverter userEntityToDtoConverter;

    public CommentEntityToDtoConverter(@Lazy BranchEntityToDtoConverter branchEntityToDtoConverter, @Lazy UserEntityToDtoConverter userEntityToDtoConverter) {
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
    }

    public CommentDto convert(Comment comment){

        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .date(comment.getDate())
                .userDto(userEntityToDtoConverter.convert(comment.getUser()))
                .build();
    }
}

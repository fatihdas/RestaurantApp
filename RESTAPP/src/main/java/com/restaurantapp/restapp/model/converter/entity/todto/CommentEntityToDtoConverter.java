package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter {

    private final UserEntityToDtoConverter userEntityToDtoConverter;
    private final BranchEntityToDtoConverter branchEntityToDtoConverter;

    public CommentEntityToDtoConverter(UserEntityToDtoConverter userEntityToDtoConverter,
                                       BranchEntityToDtoConverter branchEntityToDtoConverter) {
        this.userEntityToDtoConverter = userEntityToDtoConverter;
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
    }

    public CommentDto convert(Comment comment) {

        return CommentDto.builder()
                .id(comment.getId())
                .userDto(userEntityToDtoConverter.convert(comment.getUser()))
                .branchDto(branchEntityToDtoConverter.convert(comment.getBranch()))
                .content(comment.getContent())
                .tastePoint(comment.getTastePoint())
                .speedPoint(comment.getSpeedPoint())
                .date(comment.getDate())
                .build();
    }
}

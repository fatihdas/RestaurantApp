package com.restaurantapp.restapp.model.converter.dto.toentity;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToEntityConverter {

    private final BranchDtoToEntityConverter branchDtoToEntityConverter;

    public CommentDtoToEntityConverter(BranchDtoToEntityConverter branchDtoToEntityConverter) {
        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
    }

    public Comment convert(CommentDto commentDto) {

        return Comment.builder()
                .id(commentDto.getId())
                .date(commentDto.getDate())
                .content(commentDto.getContent())

                .build();
    }
}

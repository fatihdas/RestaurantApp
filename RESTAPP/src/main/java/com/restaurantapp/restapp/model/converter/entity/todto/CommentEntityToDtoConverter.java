package com.restaurantapp.restapp.model.converter.entity.todto;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter {

    public CommentDto convert(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(comment.getContent());
        commentDto.setDate(comment.getDate());
        commentDto.setBranchId(comment.getBranch().getId());
        commentDto.setSpeedPoint(comment.getSpeedPoint());
        commentDto.setId(comment.getId());
        commentDto.setTastePoint(comment.getTastePoint());
        commentDto.setUserId(comment.getUser().getId());

        return commentDto;
    }
}

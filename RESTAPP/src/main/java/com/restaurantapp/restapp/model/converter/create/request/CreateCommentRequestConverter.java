package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateCommentRequestConverter {

    public Comment convert(CreateCommentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("invalid request!");
        }
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setBranch(Branch.builder().id(request.getBranchId()).build());
        comment.setContent(request.getContent());
        comment.setId(request.getId());
        comment.setSpeedPoint(request.getSpeedPoint());
        comment.setTastePoint(request.getTastePoint());
        comment.setUser(User.builder().id(request.getUserId()).build());


        return comment;
    }
}

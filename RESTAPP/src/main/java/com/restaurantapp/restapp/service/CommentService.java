package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CreateCommentRequest request);

    List<CommentDto> getAllComments();

    CommentDto getComment(long id);

    String updateComment(UpdateCommentRequest request, long id);

    void deleteComment(long id);
}

package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CommentNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
import com.restaurantapp.restapp.repository.CommentRepository;
import com.restaurantapp.restapp.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentEntityToDtoConverter commentEntityToDtoConverter;
    private final CreateCommentRequestConverter createCommentRequestConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentEntityToDtoConverter commentEntityToDtoConverter,
                              CreateCommentRequestConverter createCommentRequestConverter) {
        this.commentRepository = commentRepository;
        this.commentEntityToDtoConverter = commentEntityToDtoConverter;
        this.createCommentRequestConverter = createCommentRequestConverter;
    }

    public CommentDto createComment(CreateCommentRequest request) {

        return commentEntityToDtoConverter.convert(commentRepository.save(createCommentRequestConverter.convert(request)));
    }

    public List<CommentDto> getAllComments() {

        return commentRepository.findAll().stream().map(commentEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public CommentDto getComment(long id) {

        return commentEntityToDtoConverter.convert(commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id)));
    }

    public String updateComment(UpdateCommentRequest request, long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException());

        comment.setContent(request.getContent());

        return "Comment has been updated! id:" + id;
    }

    public void deleteComment(long id) {

        commentRepository.deleteById(id);
    }
}

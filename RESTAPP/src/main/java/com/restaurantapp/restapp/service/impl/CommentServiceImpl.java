package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CommentNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CommentDto;
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
    private final BranchServiceImpl branchService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentEntityToDtoConverter commentEntityToDtoConverter,
                              CreateCommentRequestConverter createCommentRequestConverter, BranchServiceImpl branchService) {
        this.commentRepository = commentRepository;
        this.commentEntityToDtoConverter = commentEntityToDtoConverter;
        this.createCommentRequestConverter = createCommentRequestConverter;
        this.branchService = branchService;
    }

    public CommentDto createComment(CreateCommentRequest request) {

        Comment comment = commentRepository.save(createCommentRequestConverter.convert(request));
        return commentEntityToDtoConverter.convert(comment);
    }

    public List<CommentDto> getAllComments(long branchId) {

        List<Comment> commentList = commentRepository.findAllByBranchId(branchId);
        return commentList.stream().map(commentEntityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public CommentDto getComment(long id) {

        return commentEntityToDtoConverter.convert(commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id)));
    }

    public String updateComment(UpdateCommentRequest request, long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());

        comment.setContent(request.getContent());

        return "Comment has been updated!";
    }

    public void deleteComment(long id) {

        commentRepository.deleteById(id);
    }
}

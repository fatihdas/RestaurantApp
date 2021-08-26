package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
import com.restaurantapp.restapp.repository.CommentRepository;
import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentEntityToDtoConverter commentEntityToDtoConverter;

    @Mock
    private CreateCommentRequestConverter createCommentRequestConverter;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Test
    public void save() {

        CommentDto comment = this.generateComment();
        CreateCommentRequest request = CreateCommentRequest.builder().content("test comment content").build();

        Mockito.when(createCommentRequestConverter.convert(Mockito.any(CreateCommentRequest.class)))
                .thenReturn(new Comment());
        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(new Comment());
        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(comment);

        CommentDto createComment = commentServiceImpl.createComment(request);
        Assertions.assertEquals(request.getContent(), createComment.getContent());
    }

    @Test
    public void getAll() {

        List<Comment> commentList = new ArrayList<>();
        commentList.add(Comment.builder().content("test comment content").build());

        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(this.generateComment());
        Mockito.when(commentRepository.findAll()).thenReturn(commentList);

        CommentDto createCommentList = commentServiceImpl.getAllComments().get(0);

        Assertions.assertEquals(commentList.get(0).getContent(), createCommentList.getContent());
    }

    @Test
    public void getById() {

        CommentDto comment = this.generateComment();

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Comment()));
        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(comment);

        CommentDto createComment = commentServiceImpl.getComment(2);

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void update() {

        UpdateCommentRequest request = UpdateCommentRequest.builder().content("update comment").build();
        String message = "Comment has been updated!";

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(new Comment()));

        String createComment = commentServiceImpl.updateComment(new UpdateCommentRequest(), 6);

        Assertions.assertEquals(message, createComment);
    }

    private CommentDto generateComment() {
        return CommentDto.builder()
                .content("test comment content")
                .id(2)
                .build();
    }

}
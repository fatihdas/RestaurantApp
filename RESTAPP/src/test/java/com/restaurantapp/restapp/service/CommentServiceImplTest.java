package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Branch;
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

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Test
    public void save() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        CommentDto createComment = commentServiceImpl.createComment(new CreateCommentRequest());

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void getAll() {

        List<Comment> commentList = new ArrayList<>();
        commentList.add(this.generateComment());

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);

        List<CommentDto> createCommentList = commentServiceImpl.getAllComments();

        Assertions.assertEquals(commentList, createCommentList);
    }

    @Test
    public void getById() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(comment));

        CommentDto createComment = commentServiceImpl.getComment(2);

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void update() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(comment));

        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        CommentDto createComment = commentServiceImpl.updateComment(new UpdateCommentRequest(),6);

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void delete() {

        commentRepository.deleteById(2L);
        Mockito.verify(commentServiceImpl).deleteComment(2L);
    }

    private Comment generateComment() {
        return Comment.builder()
                .content("bla bla bla")
                .build();
    }

}
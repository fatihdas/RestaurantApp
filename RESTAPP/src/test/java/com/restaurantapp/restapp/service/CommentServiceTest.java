package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.model.Comment;
import com.restaurantapp.restapp.repository.CommentRepository;
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
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void save() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        Comment createComment = commentService.save(new Comment());

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void getAll() {

        List<Comment> commentList = new ArrayList<>();
        commentList.add(this.generateComment());

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);

        List<Comment> createCommentList = commentService.getAll();

        Assertions.assertEquals(commentList, createCommentList);
    }

    @Test
    public void getById() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(comment));

        Comment createComment = commentService.getById(2);

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void update() {

        Comment comment = this.generateComment();

        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(comment));

        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        Comment createComment = commentService.update(new Comment());

        Assertions.assertEquals(comment, createComment);
    }

    @Test
    public void delete() {

        commentRepository.deleteById(2L);
        Mockito.verify(commentRepository).deleteById(2L);

        String deleteComment = commentService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteComment);
    }

    private Comment generateComment() {
        return Comment.builder()
                .content("bla bla bla")
                .branch(Branch.builder().name("etilerşubesi").build())
                .build();
    }

}
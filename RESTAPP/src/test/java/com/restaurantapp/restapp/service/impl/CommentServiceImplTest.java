package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.CommentNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
import com.restaurantapp.restapp.repository.CommentRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {
    private static final long ID = 1l;
    private static final String CONTENT = "test comment";
    private static final long BRANCH_ID = 12l;
    private static final String UPDATE_MESSAGE = "Comment has been updated!";
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentEntityToDtoConverter commentEntityToDtoConverter;

    @Mock
    private CreateCommentRequestConverter createCommentRequestConverter;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void whenCreateCommentCalledByCreateCommentRequest_thenReturnSavedCommentDto() {
        CreateCommentRequest request = CreateCommentRequest.builder()
                .id(ID)
                .content(CONTENT)
                .build();
        Comment comment = Comment.builder()
                .id(ID)
                .content(CONTENT)
                .build();
        CommentDto commentDto = CommentDto.builder()
                .id(ID)
                .content(CONTENT)
                .build();

        Mockito.when(createCommentRequestConverter.convert(request)).thenReturn(comment);
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        Mockito.when(commentEntityToDtoConverter.convert(comment)).thenReturn(commentDto);

        CommentDto result = commentService.createComment(request);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(CONTENT, result.getContent());
    }

    @Test
    public void whenGetAllCommentsCalledByBranchId_thenReturnCommentDtoList() {
        List<Comment> commentList = Arrays.asList(Comment.builder()
                .content(CONTENT)
                .id(ID)
                .build());
        CommentDto commentDto = CommentDto.builder()
                .content(CONTENT)
                .id(ID)
                .build();
        Mockito.when(commentRepository.findAllByBranchId(BRANCH_ID)).thenReturn(commentList);
        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(commentDto);

        List<CommentDto> result = commentService.getAllComments(BRANCH_ID);
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(CONTENT, result.get(0).getContent());
    }

    @Test
    public void whenGetCommentCalledByValidCommentId_thenReturnCommentDto() {
        Comment comment = Comment.builder()
                .id(ID)
                .content(CONTENT)
                .build();
        CommentDto commentDto = CommentDto.builder()
                .id(ID)
                .content(CONTENT)
                .build();
        Mockito.when(commentRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(comment));
        Mockito.when(commentEntityToDtoConverter.convert(comment)).thenReturn(commentDto);

        CommentDto result = commentService.getComment(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(CONTENT, result.getContent());
    }

    @Test(expected = CommentNotFoundException.class)
    public void whenGetCommentCalledByInValidCommentId_thenThrowCommentNotFoundException() {
        Mockito.when(commentRepository.findById(ID)).thenReturn(Optional.empty());
        commentService.getComment(ID);
    }

    @Test
    public void whenUpdateCommentCalledByValidCommentId_thenReturnSuccessMessage() {
        UpdateCommentRequest request = UpdateCommentRequest.builder()
                .content(CONTENT)
                .build();
        Comment comment = Comment.builder()
                .content(CONTENT)
                .id(ID)
                .build();
        Mockito.when(commentRepository.findById(ID)).thenReturn(Optional.ofNullable(comment));

        String result = commentService.updateComment(request, ID);
        Assertions.assertEquals(UPDATE_MESSAGE, result);
        Assertions.assertEquals(CONTENT, comment.getContent());
    }

    @Test(expected = CommentNotFoundException.class)
    public void whenUpdateCommentCalledByInValidCommentId_thenThrowCommentNotFoundException(){
        Mockito.when(commentRepository.findById(ID)).thenReturn(Optional.empty());
        commentService.updateComment(new UpdateCommentRequest(),ID);
    }

    @Test
    public void whenDeleteCommentCalled_thenReturnNothing(){
        commentService.deleteComment(ID);
        Mockito.verify(commentRepository).deleteById(ID);
    }
}
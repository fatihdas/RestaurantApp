package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    private static final String CONTENT = "Harika lezzer";
    private static final int TASTE_POINT = 9;
    private static final int SPEED_POINT = 8;
    private static final long USER_ID = 1l;
    private static final long BRANCH_ID = 22L;
    @Mock
    private CommentServiceImpl commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    public void whenCreateCommentCalledByCreateCommentRequest_thenReturnSavedComment() {
        CommentDto commentDto = this.generateComment();
        CreateCommentRequest request = CreateCommentRequest.builder()
                .tastePoint(TASTE_POINT)
                .speedPoint(SPEED_POINT)
                .userId(USER_ID)
                .content(CONTENT)
                .build();
        Mockito.when(commentService.createComment(request)).thenReturn(commentDto);
        ResponseEntity<CommentDto> responseEntity = commentController.createComment(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(TASTE_POINT, responseEntity.getBody().getTastePoint());
        Assertions.assertEquals(SPEED_POINT, responseEntity.getBody().getSpeedPoint());
        Assertions.assertEquals(USER_ID, responseEntity.getBody().getUserId());
        Assertions.assertEquals(CONTENT, responseEntity.getBody().getContent());
    }

    @Test
    public void whenGetAllCommentByBranchIdCalledByBranchId_thenReturnCommentDtoList() {
        List<CommentDto> commentDtoList = Arrays.asList(this.generateComment());
        Mockito.when(commentService.getAllComments(BRANCH_ID)).thenReturn(commentDtoList);
        ResponseEntity<List<CommentDto>> responseEntity = commentController.getAllCommentByBranchId(BRANCH_ID);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(TASTE_POINT, responseEntity.getBody().get(0).getTastePoint());
        Assertions.assertEquals(SPEED_POINT, responseEntity.getBody().get(0).getSpeedPoint());
        Assertions.assertEquals(USER_ID, responseEntity.getBody().get(0).getUserId());
        Assertions.assertEquals(CONTENT, responseEntity.getBody().get(0).getContent());
    }

    private CommentDto generateComment() {
        return CommentDto.builder()
                .content(CONTENT)
                .tastePoint(TASTE_POINT)
                .speedPoint(SPEED_POINT)
                .userId(USER_ID)
                .build();
    }
}
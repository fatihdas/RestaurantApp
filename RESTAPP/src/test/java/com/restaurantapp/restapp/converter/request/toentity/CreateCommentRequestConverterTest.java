package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class CreateCommentRequestConverterTest {

    private static final String CONTENT = "comment";
    private static final int BRANCH_ID = 1;
    private static final int ID = 23;
    private static final int SPEED_POINT = 9;
    private static final int TASTE_POINT = 8;
    private static final Date DATE = new Date();
    private static final int USER_ID = 2;

    @Spy
    private CreateCommentRequestConverter createCommentRequestConverter;

    @Test
    public void whenConvertCalledByCreateCommentRequest_thenReturnComment() {

        CreateCommentRequest comment = this.generateComment();
        Comment commentActual = createCommentRequestConverter.convert(comment);

        Assertions.assertEquals(ID, commentActual.getId());
        Assertions.assertEquals(USER_ID, commentActual.getUser().getId());
        Assertions.assertEquals(TASTE_POINT, commentActual.getTastePoint());
        Assertions.assertEquals(SPEED_POINT, commentActual.getSpeedPoint());
        Assertions.assertEquals(BRANCH_ID, commentActual.getBranch().getId());
        Assertions.assertEquals(CONTENT, commentActual.getContent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConvertCalledByNullRequest_thenThrowIllegalArgumentException(){
        createCommentRequestConverter.convert(null);
    }

    private CreateCommentRequest generateComment() {

        return CreateCommentRequest.builder()
                .content(CONTENT)
                .branchId(BRANCH_ID)
                .id(ID)
                .speedPoint(SPEED_POINT)
                .tastePoint(TASTE_POINT)
                .userId(USER_ID)
                .build();
    }
}
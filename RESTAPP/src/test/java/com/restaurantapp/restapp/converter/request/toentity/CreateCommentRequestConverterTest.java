package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.BranchIdToEntityConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.UserIdToEntityConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Mock
    private UserIdToEntityConverter userIdToEntityConverter;

    @Mock
    private BranchIdToEntityConverter branchIdToEntityConverter;

    @InjectMocks
    private CreateCommentRequestConverter createCommentRequestConverter;

    @Test
    public void convert() {

        CreateCommentRequest comment = this.generateComment();
        Mockito.when(userIdToEntityConverter.convert(Mockito.anyLong())).thenReturn(User.builder().id(2).build());
        Mockito.when(branchIdToEntityConverter.convert(Mockito.anyLong())).thenReturn(Branch.builder().id(1).build());
        Comment commentActual = createCommentRequestConverter.convert(comment);

        Assertions.assertEquals(ID, commentActual.getId());
        Assertions.assertEquals(USER_ID, commentActual.getUser().getId());
        Assertions.assertEquals(DATE, commentActual.getDate());
        Assertions.assertEquals(TASTE_POINT, commentActual.getTastePoint());
        Assertions.assertEquals(SPEED_POINT, commentActual.getSpeedPoint());
        Assertions.assertEquals(BRANCH_ID, commentActual.getBranch().getId());
        Assertions.assertEquals(CONTENT, commentActual.getContent());
    }

    private CreateCommentRequest generateComment() {

        return CreateCommentRequest.builder()
                .content(CONTENT)
                .branchId(BRANCH_ID)
                .id(ID)
                .speedPoint(SPEED_POINT)
                .tastePoint(TASTE_POINT)
                .date(DATE)
                .userId(USER_ID)
                .build();
    }
}
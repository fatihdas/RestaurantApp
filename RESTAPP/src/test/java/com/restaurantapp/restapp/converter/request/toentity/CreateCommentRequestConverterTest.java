package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class CreateCommentRequestConverterTest {

    @Spy
    @InjectMocks
    private CreateCommentRequestConverter createCommentRequestConverter;

    @Test
    public void convert() {

        Comment commentExpected = this.generateComment();

        Mockito.doReturn(commentExpected).when(createCommentRequestConverter)
                .convert(Mockito.any(CreateCommentRequest.class));
        Comment commentActual = createCommentRequestConverter.convert(new CreateCommentRequest());
        Assertions.assertEquals(commentExpected, commentActual);
    }

    private Comment generateComment() {

        return Comment.builder()
                .content("comment")
                .branch(Branch.builder().build())
                .id(23)
                .speedPoint(9)
                .tastePoint(8)
                .date(new Date())
                .user(User.builder().build())
                .build();
    }
}
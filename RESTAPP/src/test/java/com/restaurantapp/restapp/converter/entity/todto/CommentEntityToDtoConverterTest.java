//package com.restaurantapp.restapp.converter.entity.todto;
//
//import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.dto.CommentDto;
//import com.restaurantapp.restapp.model.dto.UserDto;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Comment;
//import com.restaurantapp.restapp.model.entity.User;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Date;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CommentEntityToDtoConverterTest {
//
//    public static final String CONTENT = "comment";
//    public static final int ID = 23;
//    public static final int SPEED_POINT = 9;
//    public static final int TASTE_POINT = 8;
//    public static final Date DATE = new Date();
//
//    @Mock
//    private UserEntityToDtoConverter userEntityToDtoConverter;
//
//    @Mock
//    private BranchEntityToDtoConverter branchEntityToDtoConverter;
//
//    @InjectMocks
//    private CommentEntityToDtoConverter commentEntityToDtoConverter;
//
//    @Test
//    public void convert() {
//
//        Comment comment = this.generateComment();
//        Mockito.when(userEntityToDtoConverter.convert(Mockito.any(User.class))).thenReturn(new UserDto());
//        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(new BranchDto());
//        CommentDto commentActual = commentEntityToDtoConverter.convert(comment);
//
//        Assertions.assertEquals(ID, commentActual.getId());
//        Assertions.assertEquals(CONTENT, commentActual.getContent());
//        Assertions.assertEquals(SPEED_POINT, commentActual.getSpeedPoint());
//        Assertions.assertEquals(TASTE_POINT, commentActual.getTastePoint());
//        Assertions.assertEquals(DATE, commentActual.getDate());
//    }
//
//    Comment generateComment() {
//
//        return Comment.builder()
//                .content(CONTENT)
//                .branch(Branch.builder().build())
//                .id(ID)
//                .speedPoint(SPEED_POINT)
//                .tastePoint(TASTE_POINT)
//                .date(DATE)
//                .user(User.builder().build())
//                .build();
//    }
//}
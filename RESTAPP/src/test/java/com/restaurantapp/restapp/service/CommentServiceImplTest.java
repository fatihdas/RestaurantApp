//package com.restaurantapp.restapp.service;
//
//import com.restaurantapp.restapp.model.converter.create.request.CreateCommentRequestConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.CommentEntityToDtoConverter;
//import com.restaurantapp.restapp.model.dto.CommentDto;
//import com.restaurantapp.restapp.model.entity.Address;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Comment;
//import com.restaurantapp.restapp.model.entity.Menu;
//import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
//import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
//import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
//import com.restaurantapp.restapp.repository.CommentRepository;
//import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
//import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CommentServiceImplTest {
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @Mock
//    private CommentRepository commentRepository;
//
//    @Mock
//    private CommentEntityToDtoConverter commentEntityToDtoConverter;
//
//    @Mock
//    private CreateCommentRequestConverter createCommentRequestConverter;
//
//    @Mock
//    private BranchServiceImpl branchService;
//
//    @InjectMocks
//    private CommentServiceImpl commentServiceImpl;
//
//    @Test
//    public void save() {
//
//        CommentDto comment = this.generateComment();
//        CreateCommentRequest request = this.generateCreateCommentRequest();
//
//        Mockito.when(createCommentRequestConverter.convert(Mockito.any(CreateCommentRequest.class)))
//                .thenReturn(new Comment());
//        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(new Comment());
//        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(comment);
//
//        CommentDto createComment = commentServiceImpl.createComment(request);
//        Assertions.assertEquals(request.getContent(), createComment.getContent());
//    }
//
//    @Test
//    public void getAll() {
//
//        List<CommentDto> commentList = new ArrayList<>();
//        commentList.add(CommentDto.builder().content("test comment content").build());
//
//        Mockito.when(branchService.getBranchDto(Mockito.anyLong()).getCommentDtos()).thenReturn(commentList);
//
//        CommentDto createCommentList = commentServiceImpl.getAllComments(1).get(0);
//
//        Assertions.assertEquals(commentList.get(0).getContent(), createCommentList.getContent());
//    }
//
//    @Test
//    public void getById() {
//
//        CommentDto comment = this.generateComment();
//
//        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Comment()));
//        Mockito.when(commentEntityToDtoConverter.convert(Mockito.any(Comment.class))).thenReturn(comment);
//
//        CommentDto createComment = commentServiceImpl.getComment(2);
//
//        Assertions.assertEquals(comment, createComment);
//    }
//
//    @Test
//    public void update() {
//
//        UpdateCommentRequest request = UpdateCommentRequest.builder().content("update comment").build();
//        String message = "Comment has been updated!";
//
//        Mockito.when(commentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(new Comment()));
//
//        String createComment = commentServiceImpl.updateComment(new UpdateCommentRequest(), 6);
//
//        Assertions.assertEquals(message, createComment);
//    }
//
//    private CommentDto generateComment() {
//        return CommentDto.builder()
//                .content("test comment content")
//                .id(2)
//                .build();
//    }
//
//    private Branch generateBranch() {
//        return Branch.builder()
//                .name("etilerşubesi")
//                .menu(Menu.builder().build())
//                .address(Address.builder().content("testContent").build())
//                .id(22)
//                .branchStatus(BranchStatus.WAITING)
//                .commentList(new ArrayList<>())
//                .build();
//    }
//
//    private CreateCommentRequest generateCreateCommentRequest(){
//        return CreateCommentRequest.builder()
//                .content("test comment content")
//                .branchId(22)
//                .userId(1)
//                .id(11)
//                .date(new Date())
//                .speedPoint(10)
//                .tastePoint(10)
//                .build();
//    }
//
//}
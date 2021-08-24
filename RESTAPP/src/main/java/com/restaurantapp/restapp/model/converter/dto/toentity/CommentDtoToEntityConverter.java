//package com.restaurantapp.restapp.model.converter.dto.toentity;
//
//import com.restaurantapp.restapp.model.dto.CommentDto;
//import com.restaurantapp.restapp.model.entity.Comment;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CommentDtoToEntityConverter {
//
//    private final BranchDtoToEntityConverter branchDtoToEntityConverter;
//    private final UserDtoToEntityConverter userDtoToEntityConverter;
//
//    public CommentDtoToEntityConverter(BranchDtoToEntityConverter branchDtoToEntityConverter, UserDtoToEntityConverter userDtoToEntityConverter) {
//        this.branchDtoToEntityConverter = branchDtoToEntityConverter;
//        this.userDtoToEntityConverter = userDtoToEntityConverter;
//    }
//
//    public Comment convert(CommentDto commentDto) {
//
//        return Comment.builder()
//                .id(commentDto.getId())
//                .date(commentDto.getDate())
//                .content(commentDto.getContent())
//                .user(userDtoToEntityConverter.convert(commentDto.getUserDto()))
//                .tastePoint(commentDto.getTestePoint())
//                .speedPoint(commentDto.getSpeedPoint())
//                .branch(branchDtoToEntityConverter.convert(commentDto.getBranchDto()))
//                .build();
//    }
//}

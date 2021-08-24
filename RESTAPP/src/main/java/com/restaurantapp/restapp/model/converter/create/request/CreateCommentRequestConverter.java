package com.restaurantapp.restapp.model.converter.create.request;

import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentRequestConverter {

    private final UserIdToEntityConverter userIdToEntityConverter;
    private final BranchIdToEntityConverter branchIdToEntityConverter;

    public CreateCommentRequestConverter(UserIdToEntityConverter userIdToEntityConverter, BranchIdToEntityConverter branchIdToEntityConverter) {
        this.userIdToEntityConverter = userIdToEntityConverter;
        this.branchIdToEntityConverter = branchIdToEntityConverter;
    }


    public Comment convert(CreateCommentRequest request) {

        return Comment.builder()
                .id(request.getId())
                .user(userIdToEntityConverter.convert(request.getUserId()))
                .content(request.getContent())
                .tastePoint(request.getTestePoint())
                .speedPoint(request.getSpeedPoint())
                .date(request.getDate())
                .branch(branchIdToEntityConverter.convert(request.getBranchId()))
                .build();
    }
}

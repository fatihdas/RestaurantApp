package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.UserDtoToEntityConverter;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentRequestConverter {

    private final UserDtoToEntityConverter userDtoToEntityConverter;

    public CreateCommentRequestConverter(UserDtoToEntityConverter userDtoToEntityConverter) {
        this.userDtoToEntityConverter = userDtoToEntityConverter;
    }

    public Comment convert(CreateCommentRequest request) {

        return Comment.builder()
                .date(request.getDate())
                .user(userDtoToEntityConverter.convert(request.getUserDto()))
                .content(request.getContent())
                .build();
    }
}

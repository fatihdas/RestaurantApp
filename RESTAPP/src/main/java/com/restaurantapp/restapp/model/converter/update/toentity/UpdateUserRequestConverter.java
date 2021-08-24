//package com.restaurantapp.restapp.model.converter.update.toentity;
//
//import com.restaurantapp.restapp.model.converter.dto.toentity.AddressDtoToEntityConverter;
//import com.restaurantapp.restapp.model.converter.dto.toentity.CommentDtoToEntityConverter;
//import com.restaurantapp.restapp.model.entity.User;
//import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class UpdateUserRequestConverter {
//
//    private final CommentDtoToEntityConverter commentDtoToEntityConverter;
//    private final AddressDtoToEntityConverter addressDtoToEntityConverter;
//
//    public UpdateUserRequestConverter(CommentDtoToEntityConverter commentDtoToEntityConverter,
//                                      AddressDtoToEntityConverter addressDtoToEntityConverter) {
//        this.commentDtoToEntityConverter = commentDtoToEntityConverter;
//        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
//    }
//
//    public User convert(UpdateUserRequest request) {
//
//        return User.builder()
////                .roles(request.getRoles())
//                .name(request.getName())
//                .password(request.getPassword())
//                .email(request.getEmail())
//                .build();
//    }
//}

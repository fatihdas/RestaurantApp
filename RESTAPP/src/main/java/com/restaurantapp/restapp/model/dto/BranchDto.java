package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BranchDto implements Serializable {

    private long id;

    private String name;

    private Status status;

    private List<CommentDto> commentDtos;

    private MenuDto menuDto;

    private AddressDto addressDto;

}

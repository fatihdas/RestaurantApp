package com.restaurantapp.restapp.model.dto;

import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
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

    private BranchStatus branchStatus;

    private long menuId;

    private long addressId;

    private long restaurantId;

}

package com.restaurantapp.restapp.model.request.update;

import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBranchRequest {

    private String name;

    private BranchStatus branchStatus;
}

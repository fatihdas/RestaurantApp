package com.restaurantapp.restapp.model.request.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchPageGetRequest {

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;

    private String sortBy = "id";

    @NotNull
    private Long countyId;
}

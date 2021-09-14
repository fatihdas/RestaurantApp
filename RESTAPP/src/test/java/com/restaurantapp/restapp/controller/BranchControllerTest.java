package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BranchControllerTest {

    private static final long RESTAURANT_ID = 12L;
    private static final String NAME = "etiler";
    private static final int PAGE_SIZE = 12;
    private static final int PAGE_NO = 1;
    private static final String SORT_BY = "id";
    private static final long COUNTY_ID = 736l;
    private static final String STATUS = "waiting";
    @Mock
    private BranchServiceImpl branchService;

    @InjectMocks
    private BranchController branchController;

    @Test
    public void whenCreateBranchCalledByCreateBranchRequest_thenReturnSavedBranch() {
        CreateBranchRequest request = CreateBranchRequest.builder()
                .restaurantId(RESTAURANT_ID)
                .name(NAME)
                .build();
        BranchDto branchDto = this.generateBranch();
        Mockito.when(branchService.createBranch(request)).thenReturn(branchDto);
        ResponseEntity<BranchDto> responseEntity = branchController.createBranch(request);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(RESTAURANT_ID, responseEntity.getBody().getRestaurantId());
        Assertions.assertEquals(NAME, responseEntity.getBody().getName());
    }

    @Test
    public void whenGetNearBranchesCalledByBranchPageGetRequest_thenReturnBranchDtoList() {
        BranchPageGetRequest request = BranchPageGetRequest.builder()
                .pageSize(PAGE_SIZE)
                .pageNo(PAGE_NO)
                .sortBy(SORT_BY)
                .countyId(COUNTY_ID)
                .build();
        BranchDto branchDto = this.generateBranch();
        List<BranchDto> branchDtoList = Arrays.asList(branchDto);
        Mockito.when(branchService.getBranchesByCounty(request)).thenReturn(branchDtoList);
        ResponseEntity<List<BranchDto>> responseEntity = branchController.getNearBranches(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().get(0).getName());
    }

    @Test
    public void whenGetByStatusCalledByStatusValue_thenReturnBranchDtoList() {
        List<BranchDto> branchDtoList = Arrays.asList(this.generateBranch());
        Mockito.when(branchService.getBranchesByStatus(STATUS)).thenReturn(branchDtoList);
        ResponseEntity<List<BranchDto>> responseEntity = branchController.getByStatus(STATUS);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(NAME, responseEntity.getBody().get(0).getName());
    }

    @Test
    public void whenUpdateStatusCalledByBranchIdAndStatus_thenReturnUpdatedBranch() {
        BranchDto branchDto = this.generateBranch();
        Mockito.when(branchService.changeBranchStatus(1L, "approved")).thenReturn(branchDto);
        ResponseEntity<BranchDto> responseEntity = branchController.updateStatus(1L,"approved");
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private BranchDto generateBranch() {
        return BranchDto.builder()
                .name(NAME)
                .restaurantId(RESTAURANT_ID)
                .build();
    }
}
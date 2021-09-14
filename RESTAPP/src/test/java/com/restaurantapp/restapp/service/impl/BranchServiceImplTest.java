package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.exception.InvalidOwnerException;
import com.restaurantapp.restapp.exception.InvalidRoleException;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Restaurant;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.repository.BranchRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceImplTest {
    private static final long BRANCH_OWNERID = 1l;
    private static final String NAME = "branchname";
    private static final long ID = 1l;
    private static final long RESTAURANT_ID = 2l;
    private static final String STATUS = "waiting";

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchEntityToDtoConverter branchEntityToDtoConverter;

    @Mock
    private CreateBranchRequestConverter createBranchRequestConverter;

    @Mock
    private StatusEnumConverter statusEnumConverter;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private BranchServiceImpl branchService;

    @Test
    public void whenCreateBranchCalled_thenReturnSavedBranchDto() {

        CreateBranchRequest request = CreateBranchRequest.builder()
                .restaurantId(RESTAURANT_ID)
                .id(ID)
                .name(NAME)
                .build();
        Branch branch = Branch.builder()
                .id(ID)
                .restaurant(Restaurant.builder()
                        .id(RESTAURANT_ID)
                        .build())
                .name(NAME)
                .build();
        BranchDto branchDto = BranchDto.builder()
                .name(NAME)
                .restaurantId(RESTAURANT_ID)
                .id(ID)
                .build();
        Mockito.when(branchRepository.getOwnerIdByRestaurantId(Mockito.anyLong())).thenReturn(BRANCH_OWNERID);
        Mockito.when(userService.isOwner(BRANCH_OWNERID)).thenReturn(true);
        Mockito.when(createBranchRequestConverter.convert(request)).thenReturn(branch);
        Mockito.when(branchRepository.save(branch)).thenReturn(branch);
        Mockito.when(branchEntityToDtoConverter.convert(branch)).thenReturn(branchDto);

        BranchDto result = branchService.createBranch(request);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(RESTAURANT_ID, result.getRestaurantId());
    }

    @Test(expected = InvalidOwnerException.class)
    public void whenCreateBranchCalledByinvalidOwner_thenThrowInvalidOwnerException() {

        CreateBranchRequest request = CreateBranchRequest.builder()
                .restaurantId(RESTAURANT_ID)
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(branchRepository.getOwnerIdByRestaurantId(Mockito.anyLong())).thenReturn(BRANCH_OWNERID);
        Mockito.when(userService.isOwner(BRANCH_OWNERID)).thenReturn(false);

        branchService.createBranch(request);
    }

    @Test
    public void whenGetBranchDtoCalledByValidBranchId_thenReturnBranchDto() {
        Branch branch = Branch.builder()
                .id(ID)
                .restaurant(Restaurant.builder()
                        .id(RESTAURANT_ID)
                        .build())
                .build();
        BranchDto branchDto = BranchDto.builder()
                .id(ID)
                .restaurantId(RESTAURANT_ID)
                .build();
        Mockito.when(branchRepository.findById(ID)).thenReturn(java.util.Optional.ofNullable(branch));
        Mockito.when(branchEntityToDtoConverter.convert(branch)).thenReturn(branchDto);

        BranchDto result = branchService.getBranchDto(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(RESTAURANT_ID, result.getRestaurantId());
    }

    @Test(expected = BranchNotFoundException.class)
    public void whenGetBranchDtoCalledByInValidBranchId_thenThrowBranchNotFoundException() {
        Mockito.when(branchRepository.findById(ID)).thenReturn(Optional.empty());
        branchService.getBranchDto(ID);
    }

    @Test
    public void whenGetBranchesByCounty_isBuyer_thenReturnBranchDtoList() {
        BranchPageGetRequest branchPageGetRequest = BranchPageGetRequest.builder()
                .pageNo(1)
                .pageSize(1)
                .sortBy("id")
                .countyId(1l)
                .build();
        Pageable paging = PageRequest.of(1, 1, Sort.by("id"));
        List<Branch> branchList = Arrays.asList(Branch.builder()
                .id(ID)
                .name(NAME).build());
        Page<Branch> branches = new PageImpl<>(branchList, paging, 1);
        BranchDto branchDto = BranchDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(userService.hasRole(UserRoles.BUYER)).thenReturn(true);
        Mockito.when(branchRepository.findBranchesByAddress_CountyId(1l, paging)).thenReturn(branches);
        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(branchDto);

        List<BranchDto> result = branchService.getBranchesByCounty(branchPageGetRequest);
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals(NAME, result.get(0).getName());

    }

    @Test(expected = InvalidRoleException.class)
    public void whenGetBranchesByCounty_isNotBuyer_thenThrowInvalidRoleException() {
        BranchPageGetRequest branchPageGetRequest = BranchPageGetRequest.builder()
                .pageNo(1)
                .pageSize(1)
                .sortBy("id")
                .countyId(1l)
                .build();
        Mockito.when(userService.hasRole(UserRoles.BUYER)).thenReturn(false);
        branchService.getBranchesByCounty(branchPageGetRequest);

    }

    @Test
    public void whenGetBranchesByStatusCalled_thenReturnBranchDtoList() {

        BranchStatus status = BranchStatus.WAITING;
        Branch branch = Branch.builder()
                .name(NAME)
                .id(ID)
                .branchStatus(status)
                .build();
        List<Branch> branchList = Arrays.asList(branch);
        BranchDto branchDto = BranchDto.builder()
                .name(NAME)
                .branchStatus(status)
                .id(ID)
                .build();
        Mockito.when(userService.hasRole(UserRoles.ADMIN)).thenReturn(true);
        Mockito.when(statusEnumConverter.convertToDatabaseColumn(STATUS)).thenReturn(status);
        Mockito.when(branchRepository.findBranchesByBranchStatus(status)).thenReturn(branchList);
        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(branchDto);

        List<BranchDto> result = branchService.getBranchesByStatus(STATUS);
        Assertions.assertEquals(NAME, result.get(0).getName());
        Assertions.assertEquals(ID, result.get(0).getId());
        Assertions.assertEquals("WAITING", result.get(0).getBranchStatus().toString());
    }

    @Test(expected = InvalidRoleException.class)
    public void whenGetBranchesByStatusCalled_isNotAdmin_thenThrowInvalidRoleException() {
        Mockito.when(userService.hasRole(UserRoles.ADMIN)).thenReturn(false);
        branchService.getBranchesByStatus(STATUS);
    }

    @Test
    public void whenChangeBranchStatusCalledByValidBranchId_thenReturnBranchDto() {
        Branch branch = Branch.builder()
                .id(ID)
                .name(NAME)
                .build();
        BranchDto branchDto = BranchDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        BranchStatus status = BranchStatus.APPROVED;
        Mockito.when(userService.hasRole(UserRoles.ADMIN)).thenReturn(true);
        Mockito.when(branchRepository.findById(ID)).thenReturn(Optional.ofNullable(branch));
        Mockito.when(statusEnumConverter.convertToDatabaseColumn("approved")).thenReturn(status);
        Mockito.when(branchRepository.save(branch)).thenReturn(branch);
        Mockito.when(branchEntityToDtoConverter.convert(branch)).thenReturn(branchDto);

        BranchDto result = branchService.changeBranchStatus(ID, "approved");
        Assertions.assertEquals(BranchStatus.APPROVED, branch.getBranchStatus());
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = BranchNotFoundException.class)
    public void whenChangeBranchStatusCalledByInValidBranchId_thenThrowBranchNotFoundException() {
        Mockito.when(userService.hasRole(UserRoles.ADMIN)).thenReturn(true);
        Mockito.when(branchRepository.findById(ID)).thenReturn(Optional.empty());

        branchService.changeBranchStatus(ID, "approved");
    }

    @Test(expected = InvalidRoleException.class)
    public void whenChangeBranchStatusCalled_isNotAdmin_thenThrowInvalidRoleException() {
        branchService.changeBranchStatus(ID, "approved");
        Mockito.when(userService.hasRole(UserRoles.ADMIN)).thenReturn(false);
    }

    @Test
    public void whenGetBranchByidCalledByValidBranchId_thenReturnBranchEntity() {
        Branch branch = Branch.builder()
                .id(ID)
                .name(NAME)
                .build();
        Mockito.when(branchRepository.findById(ID)).thenReturn(Optional.ofNullable(branch));

        Branch result = branchService.getBranchByid(ID);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test(expected = BranchNotFoundException.class)
    public void whenGetBranchByidCalledByInValidBranchId_thenThrowBranchNotFoundException() {
        Mockito.when(branchRepository.findById(ID)).thenReturn(Optional.empty());
        branchService.getBranchByid(ID);
    }
}